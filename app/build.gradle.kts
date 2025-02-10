plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.marcin.samplecleanarch"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.marcin.samplecleanarch"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":data"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.fragment:fragment-ktx:1.3.3")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation(libs.androidx.ui.tooling.preview.android)
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test:rules:1.4.0")

    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.mockito:mockito-core:3.2.4")
    androidTestImplementation("org.mockito:mockito-android:3.2.4")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    testImplementation("org.robolectric:robolectric:4.5")

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)

    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.ui.tooling)
}
