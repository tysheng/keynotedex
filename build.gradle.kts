import org.jetbrains.kotlin.gradle.dsl.*

allprojects {
    group = "es.guillermoorellana.keynotedex"
    version = "0.0.1-SNAPSHOT"
}

buildscript {
    val html_version by extra { "0.6.9" }
    val kotlin_version by extra { "1.2.31" }
    val kotlin_js_ext_version by extra { "1.0.1-pre.11-kotlin-1.2.21" }
    val kotlin_wrapper_version by extra { "16.2.1-pre.11-kotlin-1.2.21" }
    val kotlinx_coroutines_version by extra { "0.22.3" }
    val ktor_version by extra { "0.9.2-alpha-2" }
    val squash_version by extra { "0.2.4" }
    val serialization_version by extra { "0.4" }

    repositories {
        jcenter()
        google()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://kotlin.bintray.com/kotlinx")
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:$serialization_version")
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.29")
    }
}

subprojects {
    repositories {
        mavenLocal()
        jcenter()
        google()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "http://dl.bintray.com/kotlin/kotlinx.html")
        maven(url = "http://dl.bintray.com/kotlin/ktor")
        maven(url = "https://dl.bintray.com/kotlin/squash")
        maven(url = "https://kotlin.bintray.com/kotlin-js-wrappers")
    }
}

// Heroku, Herokuish deployment task
task("stage") {
    group = "distribution"
    dependsOn(":backend:release")
}
