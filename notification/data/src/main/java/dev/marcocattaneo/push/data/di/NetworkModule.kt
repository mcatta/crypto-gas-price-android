package dev.marcocattaneo.push.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import dev.marcocattaneo.push.data.BuildConfig
import dev.marcocattaneo.push.data.repositories.AlarmsRepository
import dev.marcocattaneo.push.data.repositories.PushRepository
import dev.marcocattaneo.push.data.services.AlarmsService
import dev.marcocattaneo.push.data.services.PushService
import dev.marcocattaneo.push.data.sources.AlarmsApi
import dev.marcocattaneo.push.data.sources.PushApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val contentType = "application/json".toMediaType()

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val clientBuilder = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            this.addInterceptor(interceptor)
        }
    }

    private val retrofit = Retrofit.Builder()
        .client(clientBuilder.build())
        .baseUrl("https://europe-west2-crypto-gas-price.cloudfunctions.net/")
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
        .build()

    @AlarmApiScope
    @Provides
    @Singleton
    fun provideAlarmsRepository(authenticationRepository: AuthenticationRepository): AlarmsRepository =
        AlarmsApi(retrofit.create(AlarmsService::class.java), authenticationRepository)

    @PushApiScope
    @Provides
    @Singleton
    fun providePushRepository(authenticationRepository: AuthenticationRepository): PushRepository =
        PushApi(retrofit.create(PushService::class.java), authenticationRepository)
}