import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackBundler
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

val html_version: String by rootProject.extra
val kotlin_version: String by rootProject.extra
val kotlin_js_ext_version: String by rootProject.extra
val kotlin_wrapper_version: String by rootProject.extra
val kotlinx_coroutines_version: String by rootProject.extra
val ktor_version: String by rootProject.extra
val squash_version: String by rootProject.extra
val serialization_version: String by rootProject.extra

plugins {
    id("org.jetbrains.kotlin.frontend")
    id("kotlin2js")
    id("kotlin-dce-js")
}

dependencies {
    compile(project(":common-js"))
    compile("org.jetbrains.kotlin:kotlin-stdlib-js:$kotlin_version")
    compile("org.jetbrains.kotlin:kotlin-test-js:$kotlin_version")
    compile("org.jetbrains.kotlinx:kotlinx-html-js:$html_version")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$kotlinx_coroutines_version")
    compile("org.jetbrains:kotlin-extensions:$kotlin_js_ext_version")
    compile("org.jetbrains:kotlin-react:$kotlin_wrapper_version")
    compile("org.jetbrains:kotlin-react-dom:$kotlin_wrapper_version")
}

kotlinFrontend {
    downloadNodeJsVersion = "8.7.0"
    sourceMaps = false

    npm {
        replaceVersion("kotlinx-html-js", kotlin_version)
        replaceVersion("kotlinx-html-shared", kotlin_version)
        replaceVersion("kotlin-js-library", kotlin_version)

        dependency("react", "16.1.0")
        dependency("react-dom", "16.1.0")
        dependency("react-router")
        dependency("react-router-dom")
        dependency("react-markdown")
        dependency("jquery")

        devDependency("babel-loader")
        devDependency("babel-core")
        devDependency("css-loader")
        devDependency("karma")
        devDependency("style-loader")
        devDependency("source-map-loader")
    }
    bundle<WebPackExtension>("webpack") {
        with(this as WebPackExtension) {
            publicPath = "/frontend/"
            port = 8080
            proxyUrl = "http://localhost:9090/"
            sourceMapEnabled = true
            stats = "errors-only"
        }
    }
}

tasks {
    "compileKotlin2Js"(Kotlin2JsCompile::class) {
        kotlinOptions {
            metaInfo = true
            outputFile = "${project.buildDir.path}/js/${project.name}.js"
            sourceMap = true
            sourceMapEmbedSources = "always"
            moduleKind = "commonjs"
            main = "call"
        }
    }
}

// configure<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension>
kotlin {
    experimental.coroutines = Coroutines.ENABLE
}
