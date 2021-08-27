package io.optimus.codegen

data class EnumDefinition(val values: List<String>)

data class ModelField(
	val type: String,
	val attributes: List<String> = emptyList(),
	val default: String? = null
) {
	val isNullable = type.endsWith("?")

	val isRequired = !isNullable && default == null

	val baseType = type.substringBefore("?")
}

typealias ModelDefinition = Map<String, ModelField>

data class Schema(val model: Map<String, ModelDefinition>, val enum: Map<String, EnumDefinition>)