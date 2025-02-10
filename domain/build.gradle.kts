plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")
    api("io.reactivex.rxjava3:rxjava:3.0.0")
    api("javax.inject:javax.inject:1")
}