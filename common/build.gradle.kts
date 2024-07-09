@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.app)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.jb)
}

kotlin {
    jvmToolchain(17)

    jvm()
    androidTarget()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":resources"))

                implementation(compose.runtime)
                implementation(compose.material3)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.core)
                implementation(libs.androidx.activity)
            }
        }

        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

android {
    namespace = "moko.bug"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }
}

compose {
    desktop {
        application {
            mainClass = "moko.bug.MainKt"
        }
    }
}
