plugins {
    id("mymenu.android.library.compose")
}

android {
    namespace = "com.flopez.feature.authentication.presentation"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:authentication:domain"))
}
