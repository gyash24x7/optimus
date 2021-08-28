package io.optimus.codegen

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class FieldType {
	@SerialName("String")
	STRING,

	@SerialName("String?")
	NULLABLE_STRING,

	@SerialName("Boolean")
	BOOLEAN,

	@SerialName("Boolean?")
	NULLABLE_BOOLEAN,

	@SerialName("Int")
	INT,

	@SerialName("Int?")
	NULLABLE_INT,

	@SerialName("Double")
	DOUBLE,

	@SerialName("Double?")
	NULLABLE_DOUBLE,

	@SerialName("Enum")
	ENUM,

	@SerialName("Enum?")
	NULLABLE_ENUM;

	fun isNullable() =
		listOf(NULLABLE_DOUBLE, NULLABLE_ENUM, NULLABLE_INT, NULLABLE_STRING, NULLABLE_BOOLEAN).contains(this)
}

@Serializable
data class EnumDefinition(val values: List<String>)

@Serializable
data class ModelField(
	val type: FieldType,
	val attributes: List<String> = emptyList(),
	val default: String? = null,
	val source: String? = null
) {
	val isRequired = !type.isNullable() && default == null
}

typealias ModelDefinition = Map<String, ModelField>

@Serializable
data class Schema(val models: Map<String, ModelDefinition>, val enums: Map<String, EnumDefinition>)