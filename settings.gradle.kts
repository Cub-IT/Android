pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CubIT"
include(":app")
include(":common:network")
include(":common:ui")
include(":data:group")
include(":data:user")
include(":feature:auth")
include(":feature:group")
