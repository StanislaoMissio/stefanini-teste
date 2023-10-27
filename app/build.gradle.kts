plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.github.stefaniniteste"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.stefaniniteste"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.github.stefaniniteste.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    val navVersion = "2.7.4"
    val retrofitVersion = "2.9.0"
    val loggingOkhttpVersion = "4.11.0"
    val okhttpVersion = "4.11.0"

    //KotlinX Serializer
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-video:2.4.0")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.5.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$loggingOkhttpVersion")

    //Compose
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:$navVersion")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //AndroidX
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")

    //Unit Test Implementation
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:$okhttpVersion")

    //Instrumented Test Implementation
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    androidTestImplementation("io.mockk:mockk:1.10.5")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:$okhttpVersion")
}