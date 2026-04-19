plugins {
    id("mymenu.android.application")
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.flopez.mymenu"

    defaultConfig {
        applicationId = "com.flopez.mymenu"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:authentication:di"))
    implementation(project(":feature:authentication:presentation"))

    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Firebase
    implementation(platform(libs.firebase.bom))
}
