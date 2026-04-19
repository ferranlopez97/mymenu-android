plugins {
    id("mymenu.android.library")
}

android {
    namespace = "com.flopez.feature.authentication.di"
}

dependencies {
    implementation(project(":feature:authentication:domain"))
    implementation(project(":feature:authentication:data"))
    implementation(project(":feature:authentication:presentation"))
    implementation(libs.koin.android)
}
