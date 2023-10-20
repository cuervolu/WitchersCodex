plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("com.google.relay") version "0.3.02"
}



android {
    namespace = "com.cuervolu.thewitcherscodex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cuervolu.thewitcherscodex"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "CLIENT_ID", "\"${System.getenv("TWITCH_CLIENT_ID")}\"")
        buildConfigField("String", "Authorization", "\"${System.getenv("TWITCH_AUTHORIZATION")}\"")
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.material:material:1.5.3")
    implementation("com.github.bumptech.glide:glide:5.0.0-rc01")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("com.airbnb.android:lottie:6.1.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("id.zelory:compressor:3.0.1")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.3")
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics-ktx")
    //Auth
    implementation("com.google.firebase:firebase-auth-ktx")
    //Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")
    //Storage
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation("com.google.firebase:firebase-appcheck-playintegrity:17.0.1")
    implementation("com.google.firebase:firebase-appcheck-safetynet:16.1.2")
    //Compose Navigation
    val nav_version = "2.7.4"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Compose Foundation
    implementation("androidx.compose.foundation:foundation:1.5.3")

    //Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.2-alpha")

    implementation("androidx.core:core-splashscreen:1.0.1")
}