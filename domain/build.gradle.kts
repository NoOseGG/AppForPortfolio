plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    testImplementation(libs.test.junit.api)
    testImplementation(libs.test.junit.params)
    testRuntimeOnly(libs.test.junit.engine)
    testImplementation(libs.test.mockk)

}