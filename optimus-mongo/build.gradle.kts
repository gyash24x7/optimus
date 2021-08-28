import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val kotlinVersion: String by project
val serializationVersion: String by project
val kotestVersion: String by project
val kmongoVersion: String by project

plugins {
	kotlin("jvm")
	kotlin("plugin.serialization")
	id("com.github.johnrengelman.shadow")
	`maven-publish`
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

	implementation("org.litote.kmongo:kmongo-serialization:$kmongoVersion")

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