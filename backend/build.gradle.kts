import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.dsl.*
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.frontend.util.withTask
import org.jetbrains.kotlin.gradle.plugin.*

val html_version: String by rootProject.extra
val kotlin_version: String by rootProject.extra
val kotlin_js_ext_version: String by rootProject.extra
val kotlin_wrapper_version: String by rootProject.extra
val kotlinx_coroutines_version: String by rootProject.extra
val ktor_version: String by rootProject.extra
val squash_version: String by rootProject.extra
val serialization_version: String by rootProject.extra

plugins {
    application
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "2.0.2"
}

application {
    mainClassName = "es.guillermoorellana.keynotedex.backend.ServerKt"
}

java.sourceSets {
    "release" {

        withGroovyBuilder {
            "kotlin"{
                "srcDirs += sourceSets.main.kotlin.srcDirs"
            }
        }

        resources {
            srcDirs += getByName("main").resources.srcDirs
        }

        compileClasspath += getByName("main").compileClasspath
        runtimeClasspath += getByName("main").runtimeClasspath
    }
}

withTask<KotlinJvmCompile> {
    it.kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    compile(project(":common-jvm"))

    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    compile("io.ktor:ktor-locations:$ktor_version")
    compile("io.ktor:ktor-gson:$ktor_version")
    compile("io.ktor:ktor-html-builder:$ktor_version")
    compile("io.ktor:ktor-server-netty:$ktor_version")
    compile("org.ehcache:ehcache:3.0.0.m4")
    compile("org.jetbrains.squash:squash-h2:$squash_version")
    testCompile("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testCompile("io.ktor:ktor-server-test-host:$ktor_version")
    testCompile("org.jsoup:jsoup:1.9.1")
    compile("com.google.code.gson:gson:2.8.0")
    compile("ch.qos.logback:logback-classic:1.2.1")
}

// configure<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension>
kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks {

    val releaseJar by creating(ShadowJar::class) {
        manifest {
            attributes(mapOf("Main-Class" to "es.guillermoorellana.keynotedex.backend.ServerKt"))
        }
        classifier = "release"
        from(listOf(java.sourceSets["release"].output, java.sourceSets["main"].output))
        configurations = listOf(project.configurations.compile)
    }

    val release by creating {
        dependsOn("clean")
        dependsOn("releaseJar")
    }
    releaseJar.mustRunAfter(getTasksByName("clean", false))

    val copyWebBundle by creating(Copy::class) {
        dependsOn(":web:build")
        from("../web/build/bundle")
        into("build/resources/release")
    }
    getTasksByName("processReleaseResources", false).map { it.dependsOn(copyWebBundle) }
}
