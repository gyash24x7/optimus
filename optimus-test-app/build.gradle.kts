import io.optimus.codegen.OptimusSql

plugins {
	kotlin("jvm")
	id("io.optimus.codegen") version "1.0-SNAPSHOT"
}

dependencies {
	implementation(kotlin("stdlib"))
}

optimus {
	features = listOf(OptimusSql)
	schemaFilePath = "src/main/resources/schema.toml"
}