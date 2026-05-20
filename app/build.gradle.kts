
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
//    alias(libs.plugins.google.services)
}

android {
    namespace  = "com.example.wayflock"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.wayflock"
        minSdk        = 26          // Location & ForegroundService APIs are stable from 26+
        targetSdk     = 35
        versionCode   = 1
        versionName   = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // ── Required for Maps SDK ─────────────────────────────────
        manifestPlaceholders["MAPS_API_KEY"] = findProperty("MAPS_API_KEY") ?: ""
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Configure Kotlin compiler target for AGP 9 (Kotlin plugin is provided by AGP)
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
        // Use the new compilerOptions DSL (Kotlin Gradle plugin 2.x / AGP 9)
        compilerOptions {
            // set jvm target to 17
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget("17"))
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    // ── AndroidX Core ─────────────────────────────────────────────────────
    implementation(libs.androidx.core.ktx)

    // ── Compose BOM ───────────────────────────────────────────────────────
    // Import BOM first — it controls all compose library versions below
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.activity.compose)

    // ── Lifecycle & ViewModel ─────────────────────────────────────────────
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    // ── Navigation ────────────────────────────────────────────────────────
    implementation(libs.navigation.compose)

    // ── Hilt ──────────────────────────────────────────────────────────────
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // ── Coroutines ────────────────────────────────────────────────────────
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.play.services)  // for Tasks.await() with Firebase

/*
    // ── Firebase ──────────────────────────────────────────────────────────
    // BOM manages all Firebase versions — no version numbers needed below
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.database)
*/

    // ── Maps & Location ───────────────────────────────────────────────────
    implementation(libs.maps.compose)
    implementation(libs.play.services.location)

    // ── Room ──────────────────────────────────────────────────────────────
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // ── DataStore ─────────────────────────────────────────────────────────
    implementation(libs.datastore.preferences)

    // ── Permissions ───────────────────────────────────────────────────────
    implementation(libs.accompanist.permissions)

    // ── Serialization ─────────────────────────────────────────────────────
    implementation(libs.kotlinx.serialization.json)

    // Allows you to use the full list of Material Icons (Hiking, DirectionsBike, etc.)
    implementation(libs.compose.material.icons.extended)

    // Image Loading for AsyncImage
    implementation(libs.coil.compose)
    // ── Debug only ────────────────────────────────────────────────────────
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // ── Testing ───────────────────────────────────────────────────────────
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)
}