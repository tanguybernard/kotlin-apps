import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.spring") version "1.8.0"
    kotlin("plugin.jpa") version "1.8.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val cucumberVersion = "7.2.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("io.cucumber:cucumber-java:6.10.4")
    testImplementation("io.cucumber:cucumber-junit:6.10.4")
    implementation("io.cucumber:cucumber-spring:6.10.4")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:6.10.4")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.7.2")
    testImplementation("io.rest-assured:rest-assured:5.2.1")
    testImplementation("io.cucumber:cucumber-java8:4.7.4")


}

configurations {
    register("cucumberRuntime") {
        extendsFrom(testImplementation.get())
    }
}



tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

