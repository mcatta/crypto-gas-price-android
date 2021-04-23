package dev.marcocattaneo.cryptogasprice.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.marcocattaneo.cryptogasprice.repository.AuthenticationSource
import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {

    @Singleton
    @Binds
    abstract fun provideAuthenticationSource(authenticationSource: AuthenticationSource): AuthenticationRepository

}