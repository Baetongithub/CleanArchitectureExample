object Versions {
    const val AGP = "7.3.1"
    const val kotlin = "1.8.0"
    const val hilt = "2.44.2"
    const val androidCore = "1.9.0"
    const val appCompat = "1.6.0"
    const val material = "1.7.0"
    const val constraint = "2.1.4"
    const val fragment = "1.5.5"
    const val jUnit = "4.13.2"
    const val extJUnit = "1.1.5"
    const val espresso = "3.5.1"
    const val room = "2.5.0"
    const val lifecycle = "2.5.1"
    const val coroutinesCore = "1.6.4"
    const val inject = "1"
    const val navigation = "2.5.3"
}

object Deps {
    object UI {
        const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val room = "androidx.room:room-ktx:${Versions.room}"
    }

    object Javax {
        const val inject = "javax.inject:javax.inject:${Versions.inject}"
    }

    object DaggerHilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Lifecycle {
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    }

    object Navigation {
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    }
}

object Plugins {
    object AGP {
        const val application = "com.android.application"
        const val library = "com.android.library"
    }

    object Kotlin {
        const val android = "org.jetbrains.kotlin.android"
        const val kapt = "kotlin-kapt"
        const val jvm = "org.jetbrains.kotlin.jvm"
    }

    object DaggerHilt {
        const val hilt = "com.google.dagger.hilt.android"
        const val hiltApp = "dagger.hilt.android.plugin"

    }

    object Java {
        const val library = "java-library"
    }
}