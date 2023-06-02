plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.21"
    id("app.cash.sqldelight") version "2.0.0-alpha05"
    id("com.google.devtools.ksp") version "1.8.21-1.0.11"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-8"
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("ir.fallahpoor.cleankmmdemo.data.database")
        }
    }
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
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
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutinesCore)
                implementation(libs.ktorClientCore)
                implementation(libs.ktorKotlinxSerializationJson)
                implementation(libs.ktorClientContentNegotiation)
                implementation(libs.sqlDelightCoroutinesExtensions)
                implementation(libs.kermit)
                api(libs.kmmViewModelCore)
                api(libs.koinCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelightAndroidDriver)
                implementation(libs.ktorClientAndroid)
                implementation(libs.lifecycleViewModel)
                api(libs.koinAndroid)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(libs.sqlDelightNativeDriver)
                implementation(libs.ktorClientDarwin)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
    }
}

android {
    namespace = "ir.fallahpoor.cleankmmdemo.data"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}