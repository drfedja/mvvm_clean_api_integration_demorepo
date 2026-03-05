// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.android.dynamic.feature) apply false
    alias(libs.plugins.android.library) apply false
}

gradle.rootProject.extra["BASE_URL_DEBUG"] = "https://api.github.com"
gradle.rootProject.extra["BASE_URL_PROD"] = "https://api.github.com"
