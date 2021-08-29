package io.optimus.codegen.generators

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.github.encryptorcode.pluralize.Pluralize
import io.optimus.codegen.Schema
import io.optimus.codegen.utils.getInitializer
import io.optimus.codegen.utils.getKtTypename
import net.pearx.kasechange.toCamelCase
import net.pearx.kasechange.toPascalCase
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.nio.file.Path


fun String.pluralize(): String = Pluralize.pluralize(this);

class SqlGenerator(private val schema: Schema, private val outputDir: Path) {

	private val packageName = "io.optimus.data"

	fun generate() {
		generateEnums()
		generateTables()
	}

	private fun generateEnums() {
		schema.enums.forEach { (name, definition) ->
			val className = ClassName("$packageName.enum", name.toPascalCase())
			val enumSpec = TypeSpec.enumBuilder(className)
			definition.values.forEach {
				enumSpec.addEnumConstant(it)
			}
			val fileSpec = FileSpec.builder("$packageName.enum", name).addType(enumSpec.build()).build()
			fileSpec.writeTo(outputDir)
		}
	}

	private fun generateTables() {
		schema.models.forEach { (name, definition) ->
			val className = ClassName("$packageName.table", name.pluralize().toPascalCase())
			val tableSpec = TypeSpec.objectBuilder(className)
				.superclass(UUIDTable::class.asTypeName())
				.addSuperclassConstructorParameter(""" "${name.pluralize().toCamelCase()}" """)

			definition.forEach { (fieldName, fieldData) ->
				val fieldType = Column::class.asTypeName().parameterizedBy(fieldData.getKtTypename())
				val propertySpec = PropertySpec.builder(fieldName.toCamelCase(), fieldType)
					.initializer(fieldData.getInitializer(fieldName))
				tableSpec.addProperty(propertySpec.build())
			}

			val fileSpec = FileSpec.builder("$packageName.table", name.pluralize().toPascalCase())
				.addType(tableSpec.build()).build()
			fileSpec.writeTo(outputDir)
		}
	}
}