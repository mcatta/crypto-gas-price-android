object PluginVersions {
    const val kotlin = "1.4.32"
}

object Versions {
    const val hilt = "2.32-alpha"
    const val serialization = "1.1.0"
    const val compose = "1.0.0-beta05"
    const val coroutines = "1.3.5"
}

object DefaultConfig {
    const val minSdkVersion = 23
    const val targetSdkVersion = 30
}

object Libs {
    const val kotlinxDatetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.1.1"
    const val kotlinxSerializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"

    const val hiltCore = "com.google.dagger:hilt-core:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"

    val unitTestDependencies = listOf(
        "junit:junit:4.13.2",
        "io.mockk:mockk:1.11.0",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3"
    )

    val composeImplementations = listOf(
        "androidx.compose.ui:ui:${Versions.compose}",
        "androidx.compose.material:material:${Versions.compose}",
        "androidx.compose.ui:ui-tooling:${Versions.compose}",
        "androidx.compose.runtime:runtime-livedata:${Versions.compose}",
        "androidx.compose.runtime:runtime:${{ Versions.compose }}",
        "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha04"
    )

}