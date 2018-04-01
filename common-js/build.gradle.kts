val kotlin_version: String by getRootProject().extra
val serialization_version: String by getRootProject().extra

apply {
    plugin("kotlin-platform-js")
    plugin("kotlinx-serialization")
}

dependencies {
    add("expectedBy", project(":common"))

    add("compile", "org.jetbrains.kotlin:kotlin-stdlib-js:$kotlin_version")
    add("compile", "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serialization_version")
    add("testCompile", "org.jetbrains.kotlin:kotlin-test-js:$kotlin_version")
}
