plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.flopez.feature.authentication.di"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 26
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":feature:authentication:domain"))
    implementation(project(":feature:authentication:data"))
    implementation(project(":feature:authentication:presentation"))
    implementation(libs.koin.android)
}
