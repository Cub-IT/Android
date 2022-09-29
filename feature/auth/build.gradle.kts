plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ua.university.auth"
    compileSdk = Version.App.compileSdk

    defaultConfig {
        minSdk = Version.App.minSdk
        targetSdk = Version.App.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.Compose.composeCompiler
    }
}

dependencies {
    implementation(project(":data:user"))

    implementation(project(":common:ui"))
    implementation(project(":common:network"))

    implementation(Dependency.Ui.coreKtx)
    implementation(Dependency.Ui.lifecycleRuntimeKtx)
    implementation(Dependency.Ui.activityCompose)

    implementation(Dependency.Compose.ui)
    implementation(Dependency.Compose.uiToolingPreview)
    implementation(Dependency.Compose.material)
    debugImplementation(Dependency.Compose.uiTooling)

    implementation(Dependency.Dagger.dagger)
    implementation(Dependency.Dagger.daggerAndroidSupport)
}