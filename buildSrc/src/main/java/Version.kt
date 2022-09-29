object Version {
    object App {
        val applicationId = "ua.university.cubit"

        val minSdk = 24
        val targetSdk = 32
        val compileSdk = targetSdk

        val versionCode = 1
        val versionName = "1.0"
    }

    object Plugin {
        val androidGradlePlugin = "7.3.0"
    }

    object Kotlin {
        val kotlin = "1.7.10"
        val coroutines = "1.6.4"
    }

    object Ui {
        val coreKtx = "1.7.0"
        val lifecycle = "2.5.1"
        val activityCompose = "1.3.1"
    }

    object Compose {
        val compose = "1.2.0"
        val composeCompiler = "1.3.0"
        val material = "1.0.0-alpha11"
    }

    object Retrofit {
        val retrofit = "2.9.0"
        val okhttp = "4.10.0"
    }

    object Dagger {
        val dagger = "2.44"
        val javaxAnnotation = "1.0"
        val javaxInject = "1"
    }

    object Test {
        val junit = "4.13.2"
        val extJUnit = "1.1.3"
        val espressoCore = "3.4.0"
    }
}