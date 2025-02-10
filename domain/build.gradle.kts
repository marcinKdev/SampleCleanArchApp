plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
    api("javax.inject:javax.inject:1")
    api(libs.kotlinx.coroutines.android)
}