plugins {
    id("mymenu.android.library")
}

android {
    namespace = "com.flopez.feature.notes.di"
}

dependencies {
    implementation(project(":feature:notes:domain"))
    implementation(project(":feature:notes:data"))
    implementation(project(":feature:notes:presentation"))
}
