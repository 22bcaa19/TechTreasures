buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")  // This is needed for the plugin to work
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false  // This is the actual plugin
}