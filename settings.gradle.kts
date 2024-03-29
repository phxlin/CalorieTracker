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

rootProject.name = "CalorieTracker"
include(":app")
include(":core")
include(":core_ui")
include(":core_domain")
include(":onboarding")
include(":tracker")
include(":onboarding:onboarding_domain")
include(":onboarding:onboarding_presentation")
include(":tracker:tracker_data")
include(":tracker:tracker_domain")
include(":tracker:tracker_presentation")
