plugins {
    id("mymenu.android.library")
}

android {
    namespace = "com.flopez.feature.home.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":feature:home:domain"))
}
