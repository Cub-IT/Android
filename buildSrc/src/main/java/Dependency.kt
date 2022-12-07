object Dependency {
    object Plugin {
        // Don't forget to update deps at buildSrc (build.gradle.kts)

        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Kotlin.kotlin}"
        val kotlinAndroidExtensionsPlugin = "org.jetbrains.kotlin:kotlin-android-extensions:${Version.Kotlin.kotlin}"

        val androidGradlePlugin = "com.android.tools.build:gradle:${Version.Plugin.androidGradlePlugin}"
    }

    object Kotlin {
        val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.Kotlin.kotlin}"
        val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Kotlin.coroutines}"
        val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Kotlin.coroutines}"
        val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Version.Kotlin.dateTime}"
    }

    object Ui {
        val coreKtx = "androidx.core:core-ktx:${Version.Ui.coreKtx}"
        val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Ui.lifecycle}"
        val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Ui.lifecycle}"
        val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.Ui.lifecycle}"
        val activityCompose = "androidx.activity:activity-compose:${Version.Ui.activityCompose}"
        val navigation = "androidx.navigation:navigation-compose:${Version.Ui.navigation}"
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

    object Retrofit {
        val retrofit = "com.squareup.retrofit2:retrofit:${Version.Retrofit.retrofit}"
        val converterGson = "com.squareup.retrofit2:converter-gson:${Version.Retrofit.retrofit}"
        val okhttp = "com.squareup.okhttp3:okhttp:${Version.Retrofit.okhttp}"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.Retrofit.okhttp}"
    }

    object Dagger {
        val dagger = "com.google.dagger:dagger:${Version.Dagger.dagger}"
        val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.Dagger.dagger}"
        val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Version.Dagger.dagger}"
        val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Version.Dagger.dagger}"
        val javaxAnnotation = "javax.annotation:jsr250-api:${Version.Dagger.javaxAnnotation}"
        val javaxInject = "javax.inject:javax.inject:${Version.Dagger.javaxInject}"
    }

    object Hilt {
        val hiltAndroid = "com.google.dagger:hilt-android:${Version.Dagger.dagger}"
        val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Version.Dagger.dagger}"
        val hiltCompiler = "androidx.hilt:hilt-compiler:${Version.Hilt.hilt}"
        val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Version.Hilt.hilt}"
    }

    object Room {
        val roomRuntime = "androidx.room:room-runtime:${Version.Room.room}"
        val roomKtx = "androidx.room:room-ktx:${Version.Room.room}"
        val roomCompiler = "androidx.room:room-compiler:${Version.Room.room}"
    }

    object Test {
        val junit = "junit:junit:${Version.Test.junit}"
        val extJUnit = "androidx.test.ext:junit:${Version.Test.extJUnit}"
        val espressoCore = "androidx.test.espresso:espresso-core:${Version.Test.espressoCore}"
    }
}