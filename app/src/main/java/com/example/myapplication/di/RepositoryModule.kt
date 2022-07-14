package com.example.myapplication.di

import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.data.repository.impl.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository =
        movieRepositoryImpl
}
