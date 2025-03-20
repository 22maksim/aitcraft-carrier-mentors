plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.home"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	/**
	 * SpringBoot Starters
	 */
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	/**
	*   DataBase
	*/
//	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.kafka:spring-kafka")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
//	implementation("org.liquibase:liquibase-core")

	/**
	 * Connectors from Kafka
	 */
	implementation("org.apache.flink:flink-java:1.20.1")
	implementation("org.apache.flink:flink-runtime-web:2.0-preview1")
	implementation("org.apache.flink:flink-clients:1.20.1")
	implementation("org.apache.flink:flink-connector-kafka:3.4.0-1.20")
	implementation("org.apache.flink:flink-connector-postgres-cdc:3.3.0")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
	implementation("org.apache.flink:flink-table-api-java:1.17.0")
	implementation("org.apache.flink:flink-connector-base:1.17.0")

	implementation("io.quarkus.platform:quarkus-debezium-bom:3.19.1")

	/**
	 *  Utils && Logging
	 */
	implementation("com.wudgaby.platform:rate-limiter-core:1.1.2")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("net.logstash.logback:logstash-logback-encoder:8.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")

	/**
	 *  Docs
	 */
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")

	/**
	 *  Tests
	 */
	testImplementation("org.junit:junit-bom:5.12.0")
	testImplementation("org.testcontainers:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.assertj:assertj-core:3.27.3")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")

	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:kafka")
	testImplementation("org.testcontainers:postgresql")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
