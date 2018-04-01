import com.android.build.gradle.api.AndroidBasePlugin

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.0.0")
    }
}

apply {
    plugin("com.android.application")
    plugin("kotlin-android")
}

android {
    buildToolsVersion("27.0.1")
    compileSdkVersion(27)

    defaultConfig {
        minSdkVersion(15)
        targetSdkVersion(27)

        applicationId = "com.example.kotlingradle"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    compile(kotlin("stdlib", "1.2.0"))
}

repositories {
    jcenter()
    google()
}
