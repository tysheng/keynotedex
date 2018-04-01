val INCLUDE_ANDROID: Boolean = false

rootProject.name = "keynotedex"

include(
    "common",
    "common-jvm",
    "common-js",
    "backend",
    "web"
)

if (INCLUDE_ANDROID) {
    include("android")
}

pluginManagement {
    repositories {
        maven(url = "https://plugins.gradle.org/m2/")
    }
}
