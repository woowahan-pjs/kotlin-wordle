plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

group = "camp.nextstep.edu"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

val junitJupiterVersion = "5.10.2"
val assertJVersion = "3.25.3"

dependencies {
    testImplementation("org.junit.jupiter", "junit-jupiter", junitJupiterVersion)
    testImplementation("org.assertj", "assertj-core", assertJVersion)
}

tasks {
    test {
        useJUnitPlatform()
    }
    ktlint {
        verbose.set(true)
    }
}
