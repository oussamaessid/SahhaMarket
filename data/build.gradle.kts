plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "app.sahhamarket.data"
    compileSdk = 35

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":common:compose"))
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    /***** Room *****/
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    /***** datastore *****/
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.core)
    /***** okhttp *****/
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    /***** Retrofit *****/
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.gson)
    /***** dagger hilt *****/
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    /***** Coroutines *****/
    implementation(libs.kotlinx.coroutines.core)
//    implementation(libs.kotlinx.coroutines.services)
    implementation(libs.kotlinx.coroutines.play.services)
    /***** Map *****/
    implementation(libs.play.services.location)
    implementation(libs.play.services.maps)
    /***** Crypto *****/
    implementation(libs.androidx.security.crypto)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}