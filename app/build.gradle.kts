plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.ksp)
    //hilt
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.example.githubclient"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.githubclient"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "GITHUB_TOKEN",
            "\"${project.findProperty("GITHUB_TOKEN") as String? ?: ""}\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.junit.ktx)

    //navigation
    implementation(libs.navigation.compose)

    //unit test
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.interceptor)

    //hilt
    implementation (libs.hilt.android)
    ksp (libs.hilt.compiler)
    implementation (libs.hilt.navigation.compose)

    //glide
    implementation (libs.glide)

    //coroutines
    implementation (libs.coroutines.android)
    implementation (libs.coroutines.core)

    implementation (libs.viewmodel.lifecycle)

    //paging3
    implementation (libs.paging3)

    //room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    //location
    implementation(libs.location)
    implementation(libs.permission)

    //coil
    implementation(libs.coil)
    implementation(libs.coil.network)
}