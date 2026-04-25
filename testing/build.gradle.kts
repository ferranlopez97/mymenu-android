plugins {
    id("mymenu.kotlin.library")
}

dependencies {
    // Expuestas como api para que cualquier módulo que haga
    // testImplementation(project(":testing")) las reciba transitivamente.
    api(libs.junit.jupiter.api)
    api(libs.junit.jupiter.params)
    api(libs.turbine)
    api(libs.assertk)
    api(libs.kotlinx.coroutines.test)

    // Engine en runtime para que JUnit Platform pueda ejecutar los tests.
    runtimeOnly(libs.junit.jupiter.engine)
}
