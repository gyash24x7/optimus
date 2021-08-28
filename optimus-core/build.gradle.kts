val kotlinVersion: String by project

plugins {
	kotlin("jvm")
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
}
