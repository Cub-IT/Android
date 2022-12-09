plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "ua.university.cubit"
    compileSdk = Version.App.compileSdk

    defaultConfig {
        applicationId = Version.App.applicationId
        minSdk = Version.App.minSdk
        targetSdk = Version.App.targetSdk
        versionCode = Version.App.versionCode
        versionName = Version.App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":navigation"))

    implementation(project(":preferences"))

    implementation(project(":feature:auth"))
    implementation(project(":feature:group"))

    implementation(project(":common:ui"))
    implementation(project(":common:network"))

    implementation(Dependency.Ui.coreKtx)
    implementation(Dependency.Ui.lifecycleRuntimeKtx)
    implementation(Dependency.Ui.activityCompose)
    implementation(Dependency.Ui.navigation)

    implementation(Dependency.Compose.ui)
    implementation(Dependency.Compose.uiToolingPreview)
    implementation(Dependency.Compose.material)
    implementation("com.google.firebase:firebase-crashlytics:18.2.9")
    implementation("com.google.firebase:firebase-analytics:20.1.2")
    debugImplementation(Dependency.Compose.uiTooling)
    androidTestImplementation(Dependency.Compose.uiTestJUnit4)
    debugImplementation(Dependency.Compose.uiTestManifest)

    implementation(Dependency.Retrofit.retrofit)
    implementation(Dependency.Retrofit.converterGson)
    implementation(Dependency.Retrofit.okhttp)
    implementation(Dependency.Retrofit.loggingInterceptor)

    implementation(Dependency.Hilt.hiltAndroid)
    implementation(Dependency.Hilt.hiltNavigationCompose)
    kapt(Dependency.Hilt.hiltCompiler)
    kapt(Dependency.Hilt.hiltAndroidCompiler)

    implementation(Dependency.Room.roomRuntime)
    implementation(Dependency.Room.roomKtx)
    kapt(Dependency.Room.roomCompiler)
}