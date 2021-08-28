val kotlinPoetVersion: String by project
val kasechangeVersion: String by project
val serializationVersion: String by project
val kotlinVersion: String by project

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
	implementation("com.typesafe:config:1.4.1")

	implementation(project(":optimus-sql"))
	implementation(project(":optimus-mongo"))
}

gradlePlugin {
	plugins {
		create("optimusPlugin") {
			id = "io.optimus.codegen"
			implementationClass = "io.optimus.codegen.OptimusPlugin"
		}
	}
}