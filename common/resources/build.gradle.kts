plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "app.sahhamarket.resources"
    compileSdk = 35
}

dependencies {

    implementation(libs.material)
    api(libs.core.splashscreen)
}