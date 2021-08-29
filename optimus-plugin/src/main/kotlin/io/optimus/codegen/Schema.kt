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
	NULLABLE_ENUM
}

@Serializable
data class EnumDefinition(val values: List<String>)

@Serializable
data class FieldData(
	val type: FieldType,
	val unique: Boolean = false,
	val default: String? = null,
	val source: String? = null
)

typealias ModelDefinition = Map<String, FieldData>

@Serializable
data class Schema(val models: Map<String, ModelDefinition>, val enums: Map<String, EnumDefinition>)