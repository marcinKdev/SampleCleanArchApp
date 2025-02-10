plugins {
    id("java-library")
    id("kotlin")
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(project(":domain"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
}
