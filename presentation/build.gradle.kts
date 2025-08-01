plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "app.sahhamarket.presentation"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":common:compose"))
    implementation(project(":common:resources"))

    implementation(libs.lifecycle.viewmodel.ktx)
    /***** dagger hilt *****/
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.billing.ktx)
    kapt(libs.hilt.android.compiler)
    /***** Google map *****/
    implementation(libs.play.services.maps)
    implementation(libs.accompanist.permissions)
    implementation(libs.maps.compose)
    /***** Firebase *****/
    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}