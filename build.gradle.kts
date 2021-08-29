import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val optimusVersion: String by project

plugins {
	base
	kotlin("jvm") version "1.5.30" apply false
	kotlin("plugin.serialization") version "1.5.30" apply false
	id("com.github.johnrengelman.shadow") version "7.0.0"
}

allprojects {
	group = "io.optimus"
	version = optimusVersion

	repositories {
		mavenLocal()
		mavenCentral()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
		}
	}
}