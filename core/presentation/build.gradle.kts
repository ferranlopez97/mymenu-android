plugins {
    id("mymenu.android.library.compose")
}

android {
    namespace = "com.flopez.core.presentation"
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.androidx.compose.ui.text.google.fonts)
}
