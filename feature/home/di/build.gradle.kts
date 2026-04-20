plugins {
    id("mymenu.android.library")
}

android {
    namespace = "com.flopez.feature.home.di"
}

dependencies {
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:presentation"))
}
