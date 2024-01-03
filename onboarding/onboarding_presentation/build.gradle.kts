plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "me.yufan.onboarding_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreDomain))
    implementation(project(Modules.coreUI))
    implementation(project(Modules.onboardingDomain))
}