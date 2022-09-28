object Dependency {
    object Plugin {
        // Don't forget to update deps at buildSrc (build.gradle.kts)

        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Kotlin.kotlin}"
        val kotlinAndroidExtensionsPlugin = "org.jetbrains.kotlin:kotlin-android-extensions:${Version.Kotlin.kotlin}"

        val androidGradlePlugin = "com.android.tools.build:gradle:${Version.Plugin.androidGradlePlugin}"
    }

    object Kotlin {
        val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.Kotlin.kotlin}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Kotlin.coroutines}"
    }

    object Ui {
        val coreKtx = "androidx.core:core-ktx:${Version.Ui.coreKtx}"
        val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Ui.lifecycleRuntimeKtx}"
        val activityCompose = "androidx.activity:activity-compose:${Version.Ui.activityCompose}"
    }

    object Compose {
        val ui = "androidx.compose.ui:ui:${Version.Compose.compose}"
        val material = "androidx.compose.material3:material3:${Version.Compose.material}"
        val uiTooling = "androidx.compose.ui:ui-tooling:${Version.Compose.compose}"
        val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.Compose.compose}"
        val livedata = "androidx.compose.runtime:runtime-livedata:${Version.Compose.compose}"
        val uiTestJUnit4 = "androidx.compose.ui:ui-test-junit4:${Version.Compose.compose}"
        val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Version.Compose.compose}"
    }

    object Test {
        val junit = "junit:junit:${Version.Test.junit}"
        val extJUnit = "androidx.test.ext:junit:${Version.Test.extJUnit}"
        val espressoCore = "androidx.test.espresso:espresso-core:${Version.Test.espressoCore}"
    }
}