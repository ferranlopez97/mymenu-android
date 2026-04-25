plugins {
    id("mymenu.android.library.compose")
}

android {
    namespace = "com.flopez.feature.notes.presentation"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:notes:domain"))
}
