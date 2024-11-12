import org.gradle.internal.declarativedsl.parsing.main

plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.5"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0" // Kotlin serialization plugin
}

group = "org.example"
version = "1.0-SNAPSHOT"
val ktorVersion = "2.3.11"
val logbackVersion = "1.4.14"
val kotlinTestUnit = "1.9.10"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor", "ktor-server-netty", ktorVersion)
    implementation("io.ktor", "ktor-server-content-negotiation", ktorVersion)
    implementation("io.ktor", "ktor-server-default-headers", ktorVersion)
    implementation("io.ktor", "ktor-server-call-logging", ktorVersion)
    implementation("ch.qos.logback", "logback-classic", logbackVersion)
    implementation("io.ktor","ktor-serialization-kotlinx-json",ktorVersion)
    // Ktor status pages for custom error handling
    implementation("io.ktor:ktor-server-status-pages:2.3.0")

    // Kotlin standard library and coroutines for asynchronous programming
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")

    // Testing libraries (optional, for testing your API)
    testImplementation("io.ktor:ktor-server-test-host:2.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.0")
    implementation("io.ktor:ktor-client-core:2.3.0")
}

tasks.test {
    useJUnitPlatform()
}
application {
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(17)
}
tasks.jar {
    archiveBaseName.set("testcoverage")
}