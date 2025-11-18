plugins {
    kotlin("jvm") version "2.3.0-Beta2"
    id("com.github.johnrengelman.shadow") version "9.2.2"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.3")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
