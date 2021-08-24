plugins {
    base
    kotlin("jvm") version "1.5.21" apply false
    kotlin("plugin.serialization") version "1.5.21" apply false
}

allprojects {
    group = "io.optimus"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}