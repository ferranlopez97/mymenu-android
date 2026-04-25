plugins {
    id("mymenu.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.flopez.feature.notes.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":feature:notes:domain"))

    implementation(libs.kotlinx.serialization.json)

    // Supabase
    implementation(platform(libs.supabase.bom))
    implementation(libs.supabase.auth)
    implementation(libs.supabase.postgrest)
}
