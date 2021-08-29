val kotlinVersion: String by project
val kotestVersion: String by project
val exposedVersion: String by project
val hikariCpVersion: String by project
val postgresDriverVersion: String by project
val serializationVersion: String by project

plugins {
	kotlin("jvm")
	kotlin("plugin.serialization")
	`maven-publish`
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

	api("org.jetbrains.exposed:exposed-core:$exposedVersion")
	api("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	api("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
	api("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
	api("com.zaxxer:HikariCP:$hikariCpVersion")
	implementation("org.postgresql:postgresql:$postgresDriverVersion")

	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			from(components["java"])
		}
	}
}