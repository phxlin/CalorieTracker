plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "me.yufan.tracker_domain"
}

dependencies {
    implementation(project(Modules.core))
}