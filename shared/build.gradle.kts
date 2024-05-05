plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version (libs.versions.kotlin.get())
    id("app.cash.sqldelight") version (libs.versions.sqlDelight.get())
    id("com.google.devtools.ksp") version (libs.versions.ksp.get())
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-22"
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("ir.fallahpoor.cleankmmdemo.data.database")
        }
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
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
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }
    }
}

android {
    namespace = "ir.fallahpoor.cleankmmdemo.data"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}