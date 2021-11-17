import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Plugins

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"

	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	idea
}

idea {
	module {
		outputDir = file("build/classes/main")
		testOutputDir = file("build/classes/test")
	}
}

// App dependencies

group = "org.jdbc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	val flywayVersion = "7.8.2"
	val junitVersion = "5.7.1"
	val testcontainersVersion = "1.15.3"

	//Spring
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")

	//Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//Devtools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	//Database
	implementation("org.flywaydb", "flyway-core", flywayVersion)
	runtimeOnly("org.postgresql:postgresql")

	//Test
	testImplementation("org.junit.jupiter", "junit-jupiter", junitVersion)
	testImplementation("org.springframework.boot", "spring-boot-starter-test") {
		exclude(module = "junit")
	}
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers", "postgresql", testcontainersVersion)

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	useJUnitPlatform()
	environment("APP_ENV", "local")
	testLogging {
		events = mutableSetOf(
			org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
			org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
			org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
		)
		exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
	}
}
