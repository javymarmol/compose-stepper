import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.javymarmol.compose.stepper"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

publishing {
    repositories {
        maven {
            name = "ComposeStepper"
            url = uri("https://github.com/javymarmol/compose-stepper/")
            credentials {
                username = githubProperties["gpr.usr"] as String? ?: System.getenv("USERNAME")
                password = githubProperties["gpr.key"] as String? ?: System.getenv("TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.javymarmol.compose_stepper"
                artifactId = "ComposeStepper"
                version = "0.0.1"
                artifact("build/outputs/aar/composeStepper-release.aar")
            }
        }
    }
}