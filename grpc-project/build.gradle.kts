import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*;

val protobufVersion by extra("3.19.4")
val protobufPluginVersion by extra("0.8.14")
val grpcVersion by extra("1.40.1")

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	id("com.google.protobuf") version "0.8.17"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


	implementation("io.grpc:grpc-protobuf:${grpcVersion}")
	implementation("io.grpc:grpc-stub:1.40.1")
	implementation("io.grpc:grpc-kotlin-stub:1.1.0")
	implementation("com.google.protobuf:protobuf-java:$protobufVersion")
	implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")
	implementation("net.devh:grpc-client-spring-boot-starter:2.12.0.RELEASE")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")

	//server + client
	implementation("net.devh:grpc-spring-boot-starter:2.14.0.RELEASE")
	//server only
	implementation("net.devh:grpc-server-spring-boot-starter:2.14.0.RELEASE")


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

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:${protobufVersion}"
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
		}
		id("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:1.1.0:jdk7@jar"
		}
	}

	generateProtoTasks {
		all().forEach {
			it.plugins {
				id("grpc")
				id("grpckt")
			}
			it.builtins {
				id("kotlin")
			}
		}
	}
}