val kotestVersion: String by project
val kmongoVersion: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.litote.kmongo:kmongo-serialization:$kmongoVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")

    project(":optimus-data")
}
