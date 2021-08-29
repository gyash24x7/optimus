val kotlinVersion: String by project
val serializationVersion: String by project
val kotestVersion: String by project
val kmongoVersion: String by project

plugins {
	kotlin("jvm")
	kotlin("plugin.serialization")
	`maven-publish`
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

	api("org.litote.kmongo:kmongo-serialization:$kmongoVersion")

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