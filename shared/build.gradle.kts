import org.jetbrains.kotlin.descriptors.annotations.composeAnnotations

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    //alias(libs.plugins.kotlinKapt)
    kotlin("plugin.serialization") version "1.6.10"
    id("app.cash.sqldelight") version "2.0.1"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val ktorVersion = "2.3.2"

        commonMain.dependencies {
            //ktor
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")
            implementation("io.github.aakira:napier:2.6.1")

            //serialization
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            //sqldelight
            implementation("app.cash.sqldelight:sqlite-driver:2.0.1")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            implementation("app.cash.sqldelight:android-driver:2.0.1")

        }

        iosMain.dependencies {
            implementation("io.ktor:ktor-client-ios:$ktorVersion")
            implementation("app.cash.sqldelight:native-driver:2.0.1")
        }

    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.example.pokedex")
        }
    }
}

android {
    namespace = "com.example.pokedexkmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    implementation("androidx.compose.runtime:runtime:1.0.0")



    /*implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)*/
}
