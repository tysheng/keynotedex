import org.jetbrains.kotlin.gradle.dsl.*
import org.gradle.kotlin.dsl.*

val kotlin_version: String by getRootProject().extra
val serialization_version: String by getRootProject().extra

apply {
    plugin("kotlin-platform-common")
    plugin("kotlinx-serialization")
}

dependencies {
    add("compile", "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlin_version")
    add("compile", "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization_version")
    add("testCompile", "org.jetbrains.kotlin:kotlin-test-common:$kotlin_version")
}
