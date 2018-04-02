import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

val kotlin_version: String by getRootProject().extra
val serialization_version: String by getRootProject().extra

plugins {
    id("kotlin-platform-js")
}

apply {
    plugin("kotlinx-serialization")
}

dependencies {
    add("expectedBy", project(":common"))

    add("compile", "org.jetbrains.kotlin:kotlin-stdlib-js:$kotlin_version")
    add("compile", "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serialization_version")
    add("testCompile", "org.jetbrains.kotlin:kotlin-test-js:$kotlin_version")
}

tasks {
    "compileKotlin2Js"(Kotlin2JsCompile::class) {
        kotlinOptions {
            metaInfo = true
            sourceMap = true
            sourceMapEmbedSources = "always"
            moduleKind = "commonjs"
        }
    }
}
