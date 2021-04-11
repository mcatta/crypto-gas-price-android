package dev.marcocattaneo.gasprice.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.marcocattaneo.cryptogasprice.gasprice.data.BuildConfig
import dev.marcocattaneo.gasprice.data.services.GasPriceService
import dev.marcocattaneo.gasprice.data.sources.GasPriceApi
import dev.marcocattaneo.gasprice.domain.repositories.GasPriceRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
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
    private val clientBuilder = OkHttpClient.Builder()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(interceptor)
        }
        return Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl("https://europe-west2-crypto-gas-price.cloudfunctions.net/")
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }



    @GasApi
    @Provides
    @Singleton
    fun provideGasRepository(retrofit: Retrofit): GasPriceRepository = GasPriceApi(retrofit.create(GasPriceService::class.java))

}