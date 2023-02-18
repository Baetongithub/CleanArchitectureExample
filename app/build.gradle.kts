plugins {
    id(Plugins.AGP.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.DaggerHilt.hiltApp)
}

android {
    namespace = "com.example.cleanarchitectureexample"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.cleanarchitectureexample"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    //UI
    implementation(Deps.UI.androidCore)
    implementation(Deps.UI.appCompat)
    implementation(Deps.UI.material)
    implementation(Deps.UI.constraint)

    //Fragment
    implementation(Deps.UI.fragment)

    //Lifecycle
    implementation(Deps.Lifecycle.runtime)
    implementation(Deps.Lifecycle.viewModel)

    // room
    implementation(Deps.Room.runtime)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.room)

    //dagger-hilt
    implementation(Deps.DaggerHilt.hilt)
    kapt(Deps.DaggerHilt.compiler)

    //navigation
    implementation(Deps.Navigation.fragment)
    implementation(Deps.Navigation.ui)

    testImplementation(Deps.UI.jUnit)
    androidTestImplementation(Deps.UI.extJUnit)
    androidTestImplementation(Deps.UI.espresso)
}