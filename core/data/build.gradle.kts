plugins {
    id("mymenu.android.library")
}

android {
    namespace = "com.flopez.core.data"
}

dependencies {
    implementation(project(":core:domain"))
}
