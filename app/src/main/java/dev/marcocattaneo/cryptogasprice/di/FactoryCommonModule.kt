package dev.marcocattaneo.cryptogasprice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class FactoryCommonModule {

    @CoroutineContextScope
    @Provides
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.Default

}