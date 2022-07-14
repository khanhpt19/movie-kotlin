package com.example.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {
    @Provides
    @Singleton
    @Named("io")
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @Named("main")
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @Named("default")
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    @Named("unconfined")
    fun providesUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}
