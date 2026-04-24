plugins {
    id("mymenu.android.library")
}

android {
    namespace = "com.flopez.feature.authentication.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":feature:authentication:domain"))

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.kotlinx.coroutines.play.services)

    // Supabase
    implementation(platform(libs.supabase.bom))
    implementation(libs.supabase.auth)
}
