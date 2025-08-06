plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "app.sahhamarket.compose"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    defaultConfig{
        vectorDrawables.useSupportLibrary = true
    }
}

dependencies {

    api(project(":common:resources"))
    implementation(project(":domain"))

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
    api(libs.navigation.compose)
    api(libs.lottie.compose)
    /***** coil *****/
    api(libs.coil.compose)
    api(libs.coil.svg)
}