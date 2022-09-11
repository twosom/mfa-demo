import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version ("2.7.3")
    id("io.spring.dependency-management") version ("1.0.11.RELEASE")
    id("java")
    kotlin("jvm") version "1.7.10"
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = false

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

subprojects {
    group = "com.icloud"
    version = "0.0.1-SNAPSHOT"


    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
    }
    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        if (project.name !== "common-module") {
            implementation("org.springframework.boot:spring-boot-starter-web")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
        }
    }

    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }
}



project(":auth-server") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("mysql:mysql-connector-java:8.0.30")
    }
}

project(":business-server") {
    dependencies {
        implementation("io.github.openfeign:feign-gson:11.9.1")
        implementation("io.github.openfeign:feign-core:11.9.1")
        implementation("org.springframework.boot:spring-boot-starter-security")
    }
}