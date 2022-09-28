package ua.university.buildsrc

class Version {

    object App {
        val minSdkVersion = 24
        val targetSdkVersion = 32
        val compileSdkVersion = targetSdkVersion
    }

    object Plugin {
        val androidGradlePluginVersion = "7.3.0"
    }

    object Kotlin {
        val kotlinVersion = "1.7.10"
        val coroutinesVersion = "1.6.4"
    }

    object Compose {
        val composeVersion = "1.2.0"
        val composeCompilerVersion = "1.3.0"
    }
}