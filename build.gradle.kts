buildscript {
    /*ext {
        compose_version = "1.2.0"
    }*/
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.0" apply false
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.21" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
}