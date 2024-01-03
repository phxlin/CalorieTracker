plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "me.yufan.onboarding_domain"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreDomain))
}