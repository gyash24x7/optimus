import io.optimus.codegen.OptimusSql

val kotlinVersion: String by project

plugins {
	kotlin("jvm")
	id("io.optimus.codegen") version "1.0-SNAPSHOT"
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
}

optimus {
	features = listOf(OptimusSql)
	schemaFilePath = "src/main/resources/schema.conf"
}