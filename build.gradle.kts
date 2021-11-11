plugins {
    kotlin("jvm") version "1.5.10"
    java
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-core:1.6.5")
    implementation("io.ktor:ktor-server-netty:1.6.5")
    implementation("io.ktor:ktor-client-serialization:1.6.5")
    implementation("io.ktor:ktor-jackson:1.6.5")
    implementation("ch.qos.logback:logback-classic:1.2.5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

application {
    mainClass.set("com.samplecode.ApplicationKt")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}