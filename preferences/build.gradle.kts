plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ua.university.preferences"
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
}

dependencies {
    implementation(Dependency.Ui.coreKtx)
    implementation(Dependency.Ui.lifecycleRuntimeKtx)
    implementation(Dependency.Ui.activityCompose)

    implementation(Dependency.Retrofit.converterGson)

    implementation(Dependency.Hilt.hiltAndroid)
    implementation(Dependency.Hilt.hiltNavigationCompose)
    kapt(Dependency.Hilt.hiltCompiler)
    kapt(Dependency.Hilt.hiltAndroidCompiler)
}