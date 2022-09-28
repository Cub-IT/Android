package ua.university.buildsrc

object Dependencies {

    object Plugin {
        // Don't forget to update deps at buildSrc (build.gradle.kts)

        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Kotlin.kotlinVersion}"
        val kotlinAndroidExtensionsPlugin = "org.jetbrains.kotlin:kotlin-android-extensions:${Version.Kotlin.kotlinVersion}"

        val androidGradlePlugin = "com.android.tools.build:gradle:${Version.Plugin.androidGradlePluginVersion}"
    }

    object Kotlin {
        val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.Kotlin.kotlinVersion}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Kotlin.coroutinesVersion}"
    }

    object Compose {
        val ui = "androidx.compose.ui:ui:${Version.Compose.composeVersion}"
        val material = "androidx.compose.material:material:${Version.Compose.composeVersion}"
        val uiTooling = "androidx.compose.ui:ui-tooling:${Version.Compose.composeVersion}"
        val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.Compose.composeVersion}"
        val livedata = "androidx.compose.runtime:runtime-livedata:${Version.Compose.composeVersion}"
    }
}