plugins {
    id("mymenu.kotlin.library")
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.koin.core)
    implementation(libs.javax.inject)
}
