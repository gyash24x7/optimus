val kotlinPoetVersion: String by project
val kasechangeVersion: String by project
val serializationVersion: String by project
val kotlinVersion: String by project
val pluralizeVersion: String by project
val typesafeConfigVersion: String by project
val optimusVersion: String by project

plugins {
	kotlin("jvm")
	kotlin("plugin.serialization")
	`java-gradle-plugin`
	`maven-publish`
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-hocon:$serializationVersion")

	implementation("com.squareup:kotlinpoet:$kotlinPoetVersion")
	implementation("net.pearx.kasechange:kasechange:$kasechangeVersion")
	implementation("io.github.encryptorcode:pluralize:$pluralizeVersion")
	implementation("com.typesafe:config:$typesafeConfigVersion")

	implementation("io.optimus:optimus-sql:$optimusVersion")
	implementation("io.optimus:optimus-mongo:$optimusVersion")
}

gradlePlugin {
	plugins {
		create("optimusPlugin") {
			id = "io.optimus.codegen"
			implementationClass = "io.optimus.codegen.OptimusPlugin"
		}
	}
}