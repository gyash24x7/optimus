import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	base
	kotlin("jvm") version "1.5.30" apply false
	kotlin("plugin.serialization") version "1.5.30" apply false
	id("com.github.johnrengelman.shadow") version "7.0.0"
}

allprojects {
	group = "io.optimus"
	version = "1.0-SNAPSHOT"

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