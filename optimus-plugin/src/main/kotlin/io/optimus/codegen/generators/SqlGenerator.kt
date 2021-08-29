package io.optimus.codegen.generators

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import io.github.encryptorcode.pluralize.Pluralize
import io.optimus.codegen.Schema
import io.optimus.codegen.utils.basePackage
import io.optimus.codegen.utils.getInitializer
import io.optimus.codegen.utils.getKtTypename
import net.pearx.kasechange.toCamelCase
import net.pearx.kasechange.toPascalCase
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.nio.file.Path
import java.util.UUID


fun String.pluralize(): String = Pluralize.pluralize(this);

class SqlGenerator(private val schema: Schema, private val outputDir: Path) {

	fun generate() {
		generateEnums()
		generateTables()
		generateModels()
	}

	private fun generateEnums() {
		schema.enums.forEach { (name, definition) ->
			val className = ClassName("$basePackage.enum", name.toPascalCase())
			val enumSpec = TypeSpec.enumBuilder(className)
			definition.values.forEach {
				enumSpec.addEnumConstant(it)
			}
			val fileSpec = FileSpec.builder("$basePackage.enum", name).addType(enumSpec.build()).build()
			fileSpec.writeTo(outputDir)
		}
	}

	private fun generateTables() {
		schema.models.forEach { (name, definition) ->
			val className = ClassName("$basePackage.table", name.pluralize().toPascalCase())
			val tableSpec = TypeSpec.objectBuilder(className)
				.superclass(UUIDTable::class.asTypeName())
				.addSuperclassConstructorParameter(""" "${name.pluralize().toCamelCase()}" """)

			definition.forEach { (fieldName, fieldData) ->
				val fieldType = Column::class.asTypeName().parameterizedBy(fieldData.getKtTypename())
				val propertySpec = PropertySpec.builder(fieldName.toCamelCase(), fieldType)
					.initializer(fieldData.getInitializer(fieldName))
				tableSpec.addProperty(propertySpec.build())
			}

			val fileSpec = FileSpec.builder("$basePackage.table", name.pluralize().toPascalCase())
				.addType(tableSpec.build()).build()
			fileSpec.writeTo(outputDir)
		}
	}

	private fun generateModels() {
		schema.models.forEach { (name, definition) ->
			val className = ClassName("$basePackage.model", "${name.toPascalCase()}Model")
			val tableObjectName = ClassName("$basePackage.table", name.pluralize().toPascalCase())

			val idTypeName = EntityID::class.parameterizedBy(UUID::class)
			val idParameterSpec = ParameterSpec.builder("id", idTypeName)
			val primaryConstructorSpec = FunSpec.constructorBuilder()
				.addParameter(idParameterSpec.build())

			val companionObjectTypeName = UUIDEntityClass::class.asTypeName().plusParameter(className)
			val companionObjectSpec = TypeSpec.companionObjectBuilder()
				.superclass(companionObjectTypeName)
				.addSuperclassConstructorParameter("%T", tableObjectName)

			val modelSpec = TypeSpec.classBuilder(className)
				.superclass(UUIDEntity::class)
				.addSuperclassConstructorParameter("id")
				.primaryConstructor(primaryConstructorSpec.build())
				.addType(companionObjectSpec.build())

			definition.forEach { (fieldName, fieldData) ->
				val propertySpec = PropertySpec.builder(fieldName.toCamelCase(), fieldData.getKtTypename())
					.mutable().delegate("%T.${fieldName.toCamelCase()}", tableObjectName)
				modelSpec.addProperty(propertySpec.build())
			}

			val fileSpec = FileSpec.builder("$basePackage.model", "${name.toPascalCase()}Model")
				.addType(modelSpec.build()).build()
			fileSpec.writeTo(outputDir)
		}
	}
}