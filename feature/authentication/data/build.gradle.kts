plugins {
    id("mymenu.android.library")
}

android {
    namespace = "com.flopez.feature.authentication.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":feature:authentication:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.koin.android)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.kotlinx.coroutines.play.services)
}
