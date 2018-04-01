val kotlin_version: String by rootProject.extra
val serialization_version: String by rootProject.extra

apply {
    plugin("kotlin-platform-jvm")
    plugin("kotlinx-serialization")
}

dependencies {
    add("expectedBy", project(":common"))

    add("compile", "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    add("compile", "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version")

    add("testCompile", "junit:junit:4.12")
    add("testCompile", "org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    add("testCompile", "org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
