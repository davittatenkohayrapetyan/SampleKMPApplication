plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation("io.ktor:ktor-client-core:2.3.12")
            implementation("io.ktor:ktor-client-cio:2.3.12")
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:2.3.12")
            }
        }
        val iosArm64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.12")
            }
        }
        val iosArm64Test by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.12")
            }
        }
        val iosX64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.12")
            }
        }
        val iosSimulatorArm64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.12")
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.davithayrapetyan.samplekmpapplication"
    compileSdk = 34
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
