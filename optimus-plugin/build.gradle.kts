val kotlinPoetVersion: String by project
val kasechangeVersion: String by project
val hopliteVersion: String by project

plugins {
	kotlin("jvm")
	`java-gradle-plugin`
	`maven-publish`
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation("com.squareup:kotlinpoet:$kotlinPoetVersion")
	implementation("net.pearx.kasechange:kasechange:$kasechangeVersion")
	implementation("com.sksamuel.hoplite:hoplite-core:$hopliteVersion")
	implementation("com.sksamuel.hoplite:hoplite-toml:$hopliteVersion")

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