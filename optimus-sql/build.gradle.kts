import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val kotestVersion: String by project
val exposedVersion: String by project
val hikariCpVersion: String by project
val postgresDriverVersion: String by project

plugins {
	kotlin("jvm")
	kotlin("plugin.serialization")
	id("com.github.johnrengelman.shadow")
	`maven-publish`
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

	implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
	implementation("com.zaxxer:HikariCP:$hikariCpVersion")
	implementation("org.postgresql:postgresql:$postgresDriverVersion")

	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.named<ShadowJar>("shadowJar") {
	archiveClassifier.set("")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			artifact(tasks["shadowJar"])
		}
	}
}