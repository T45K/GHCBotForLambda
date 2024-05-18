plugins {
    kotlin("jvm") version "2.0.0-Beta5"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.4.12")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.3")
    implementation("com.twitter:twitter-api-java-sdk:1.1.4") // after this version, we can no longer use TwitterCredentialsOAuth1

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
