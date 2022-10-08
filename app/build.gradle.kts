plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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

    implementation(project(":feature:auth"))
    implementation(project(":feature:group"))

    implementation(project(":common:ui"))

    implementation(Dependency.Ui.coreKtx)
    implementation(Dependency.Ui.lifecycleRuntimeKtx)
    implementation(Dependency.Ui.activityCompose)
    implementation(Dependency.Ui.navigation)

    implementation(Dependency.Compose.ui)
    implementation(Dependency.Compose.uiToolingPreview)
    implementation(Dependency.Compose.material)
    debugImplementation(Dependency.Compose.uiTooling)
    androidTestImplementation(Dependency.Compose.uiTestJUnit4)
    debugImplementation(Dependency.Compose.uiTestManifest)
}