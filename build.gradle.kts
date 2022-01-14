group = 'mil.army.futures'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

plugins {
    val kotlinVersion = "1.6.10"
    val springBootVersion = "2.6.2"
    val springDependencyManagementVersion = "1.0.11.RELEASE"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {

    withType<Test> {
        useJUnitPlatform()

        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
        }
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    val installFrontend by register<Exec>("installFrontend") {
        inputs.file(file("$projectDir/frontend/yarn.lock"))
        inputs.file(file("$projectDir/frontend/package.json"))
        outputs.dir(file("$projectDir/frontend/node_modules"))
        commandLine("yarn", "install")
    }

    val testFrontend by register<Exec>("testFrontend") {
        dependsOn(installFrontend)
        commandLine("yarn", "test", "--watchAll=false")
    }

    val buildClient by register<Exec>("buildClient") {
        dependsOn(installFrontend)
        mustRunAfter(testFrontend)
        inputs.dir(file("$projectDir/frontend/src"))
        inputs.dir(file("$projectDir/frontend/public"))
        outputs.dir("$projectDir/frontend/build")
        commandLine("yarn", "build")
    }

    val copyFrontend by register<Sync>("copyFrontend") {
        dependsOn(buildClient)
        from("$projectDir/frontend/build")
        into(file("$buildDir/resources/main/static"))
        doLast {
            println("copied built frontend to static resources")
        }
    }

    withType<ResolveMainClassName> {
        dependsOn(copyFrontend)
    }
}