plugins {
    id("mymenu.android.library.compose")
}

android {
    namespace = "com.flopez.feature.home.presentation"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:authentication:domain"))

    implementation(libs.androidx.lifecycle.runtime.compose)
}
