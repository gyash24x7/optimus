import io.optimus.codegen.OptimusSql

val kotlinVersion: String by project
val optimusVersion: String by project

plugins {
	kotlin("jvm")
	id("io.optimus.codegen") version "1.0-SNAPSHOT"
}

sourceSets {
	main {
		java.srcDir("build/generated")
	}
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	implementation("io.optimus:optimus-sql:$optimusVersion")
}

optimus {
	features = listOf(OptimusSql)
	schemaFilePath = "src/main/resources/schema.conf"
}