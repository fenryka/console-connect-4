import org.jetbrains.kotlin.cli.jvm.compiler.findMainClass
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    application
}

group = "me.fenryka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}


tasks {

    withType<KotlinCompile>() {
        kotlinOptions.jvmTarget = "1.8"
    }
    withType<Jar> {
        manifest {
            attributes["Main-Class"] = application.mainClass
        }
        // I have no idea why this is need or what it does, but it seems important as
        // without it we get classloader issues.... I fucking hate java nad gradle
        from(configurations.runtimeClasspath.get().map {if (it.isDirectory) it else zipTree(it)})
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

application {
    mainClass.set("Connect4")
}