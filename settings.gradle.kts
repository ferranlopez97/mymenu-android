pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "mymenu"
include(":app")
include(":core:domain")
include(":core:data")
include(":core:presentation")
include(":feature:authentication:domain")
include(":feature:authentication:data")
include(":feature:authentication:presentation")
include(":feature:authentication:di")
include(":feature:home:domain")
include(":feature:home:data")
include(":feature:home:presentation")
include(":feature:home:di")
include(":feature:notes:domain")
include(":feature:notes:data")
include(":feature:notes:presentation")
include(":feature:notes:di")
