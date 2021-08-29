package io.optimus.codegen.utils

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import io.optimus.codegen.FieldData
import io.optimus.codegen.FieldType
import net.pearx.kasechange.toSnakeCase

const val basePackage = "io.optimus.data"

fun FieldData.isNullable() =
	listOf(
		FieldType.NULLABLE_DOUBLE,
		FieldType.NULLABLE_ENUM,
		FieldType.NULLABLE_INT,
		FieldType.NULLABLE_STRING,
		FieldType.NULLABLE_BOOLEAN
	).contains(type)

fun FieldData.getKtTypename(): TypeName = when (type) {
	FieldType.BOOLEAN -> Boolean::class.asTypeName()
	FieldType.STRING -> String::class.asTypeName()
	FieldType.NULLABLE_STRING -> String::class.asTypeName().copy(nullable = true)
	FieldType.NULLABLE_BOOLEAN -> Boolean::class.asTypeName().copy(nullable = true)
	FieldType.INT -> Int::class.asTypeName()
	FieldType.NULLABLE_INT -> Int::class.asTypeName().copy(nullable = true)
	FieldType.DOUBLE -> Double::class.asTypeName()
	FieldType.NULLABLE_DOUBLE -> Double::class.asTypeName().copy(nullable = true)
	FieldType.ENUM -> ClassName("$basePackage.enum", source!!)
	FieldType.NULLABLE_ENUM -> ClassName("$basePackage.enum", source!!).copy(nullable = true)
}

fun FieldData.getDefaultValue() = when (type) {
	FieldType.ENUM, FieldType.NULLABLE_ENUM -> default?.let { "$source.$it" }
	else -> default
}

fun FieldData.getInitializer(name: String): CodeBlock {
	var initializerFormat = when (type) {
		FieldType.STRING, FieldType.NULLABLE_STRING -> "varchar(%S, 1024)"
		FieldType.BOOLEAN, FieldType.NULLABLE_BOOLEAN -> "bool(%S)"
		FieldType.INT, FieldType.NULLABLE_INT -> "integer(%S)"
		FieldType.DOUBLE, FieldType.NULLABLE_DOUBLE -> "double(%S)"
		FieldType.ENUM, FieldType.NULLABLE_ENUM -> "enumerationByName(%S, 256, ${source}::class)"
	}

	if (isNullable()) initializerFormat += ".nullable()"
	if (unique) initializerFormat += ".uniqueIndex()"
	if (default != null) initializerFormat += ".default(${getDefaultValue()})"

	return CodeBlock.of(initializerFormat, name.toSnakeCase())
}