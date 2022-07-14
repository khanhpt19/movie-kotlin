package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.remote.api.ApiServices
import com.example.myapplication.isDevMode
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val TIME_OUT = 10L

    @Singleton
    @Provides
    fun provideOkHttpCache(context: Context): Cache =
        Cache(context.cacheDir, (10 * 1024 * 1024).toLong())

    @Singleton
    @Provides
    @Named("logging")
    fun provideLoginInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (isDevMode()) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    @Named("header")
    fun provideHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val baseUrl = request.url.newBuilder().addQueryParameter(
                "api_key", "2cdf3a5c7cf412421485f89ace91e373"
            ).build()
            val newRequest =
                request.newBuilder().url(baseUrl).method(request.method, request.body).build()
            chain.proceed(newRequest)
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named("logging") logging: Interceptor,
        @Named("header") header: Interceptor
    ): OkHttpClient = OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS).writeTimeout(TIME_OUT, TimeUnit.SECONDS).apply {
            addInterceptor(logging)
            addInterceptor(header)
        }.build()

    @Singleton
    @Provides
    fun provideAppRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiServices = retrofit.create(ApiServices::class.java)
}
