plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Agregar esto para habilitar KAPT
    kotlin("kapt")
}

android {
    namespace = "com.example.app_pasteleria"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.app_pasteleria"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
// Con el BOM, no necesitas especificar la versión para los artefactos de Compose.
// El BOM gestiona las versiones por ti.
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")


    // Dependencia para la navegación con Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")


    // Íconos (core opcional) y EXTENDIDOS (¡este es el clave!)
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")

    // Retrofit (Para conexión a internet)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Para convertir JSON a objetos


    // Dependencias Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.material3)  // Versión actualizada
    kapt("androidx.room:room-compiler:2.6.1")          // Misma versión
    implementation("androidx.room:room-ktx:2.6.1")     // Misma versión



    //  implementation(libs.androidx.compose.runtime.livedata)

    // Option 1: If you add it to libs.versions.toml
    //implementation(libs.androidx.compose.runtime.livedata)
    // OR Option 2: Direct notation
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")

// CameraX
    val camerax_version = "1.3.3"
    implementation("androidx.camera:camera-core:$camerax_version")
    implementation("androidx.camera:camera-camera2:$camerax_version")
    implementation("androidx.camera:camera-lifecycle:$camerax_version")
    implementation("androidx.camera:camera-view:$camerax_version")

// ZXing para leer QR
    implementation("com.google.zxing:core:3.5.3")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")

    // ML Kit Barcode Scanning
    implementation("com.google.mlkit:barcode-scanning:17.2.0")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.material3:material3")



    // Retrofit y Gson Converter
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

// Corrutinas para trabajo asincrónico
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // Jetpack Compose
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    dependencies {


        //  TEST DEPENDENCIES (CONFIGURACIÓN CORRECTA Y LIMPIA)

// Kotest (solo estas 2 son necesarias)
        testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
        testImplementation("io.kotest:kotest-assertions-core:5.8.0")

// MockK
        testImplementation("io.mockk:mockk:1.13.10")

// Coroutines Test
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")

// AndroidX Test
        testImplementation("androidx.arch.core:core-testing:2.2.0")

// JUnit 5 (solo engine, Kotest usa JUnit 5)
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")


    }



    //  implementation(libs.androidx.compose.runtime.livedata)

    // Option 1: If you add it to libs.versions.toml
    //implementation(libs.androidx.compose.runtime.livedata)
    // OR Option 2: Direct notation




    kotlin
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

tasks.withType<Test> {
    useJUnitPlatform()  // <<< NECESARIO

    testLogging {
        events("passed", "failed", "skipped")
    }
}