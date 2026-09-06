

plugins {
    kotlin("jvm") version "2.4.10"
}

group = "com.zcf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okio:okio:3.18.1")
    implementation("com.google.code.gson:gson:2.14.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")

    testImplementation(kotlin("test"))

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.11.0")
}

tasks.test {
    useJUnit()
}

kotlin {
    jvmToolchain(21)
}
