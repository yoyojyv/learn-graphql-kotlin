import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
}

group = "me.jerry.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
}

extra["graphqlSpringBootVersion"] = "11.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// graphql
	implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:${property("graphqlSpringBootVersion")}")

	// to embed GraphQL Playground tool
	runtimeOnly("com.graphql-java-kickstart:playground-spring-boot-starter:${property("graphqlSpringBootVersion")}")

	// to embed GraphiQL tool
	runtimeOnly("com.graphql-java-kickstart:graphiql-spring-boot-starter:${property("graphqlSpringBootVersion")}")

	// to embed Voyager tool
	runtimeOnly("com.graphql-java-kickstart:voyager-spring-boot-starter:${property("graphqlSpringBootVersion")}")

	// scalars extended
	implementation("com.graphql-java:graphql-java-extended-scalars:16.0.0") {
		exclude("com.graphql-java", "graphql-java")
	}

	// validator
	implementation("org.hibernate:hibernate-validator")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
