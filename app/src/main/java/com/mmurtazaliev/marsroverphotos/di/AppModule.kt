package com.mmurtazaliev.marsroverphotos.di

import android.content.Context
import android.util.Log
import com.mmurtazaliev.marsroverphotos.api.NasaApi
import com.mmurtazaliev.marsroverphotos.repository.PhotoRepository
import com.mmurtazaliev.marsroverphotos.viewmodel.DatabaseHelper
import com.mmurtazaliev.marsroverphotos.viewmodel.NetworkUtils
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(subcomponents = [MainComponent::class])
class AppModule {

    @AppScope
    @Provides
    fun provideNasaApi(): NasaApi {

        val baseUrl = "https://api.nasa.gov/mars-photos/api/v1/"
        val logger =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("NASA_API", it) })
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl.toHttpUrlOrNull()!!)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NasaApi::class.java)
    }
}
