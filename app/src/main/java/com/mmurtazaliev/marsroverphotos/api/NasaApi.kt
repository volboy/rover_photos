package com.mmurtazaliev.marsroverphotos.api

import android.util.Log
import com.squareup.moshi.Json
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface NasaApi {
    @GET("rovers/curiosity/photos?sol=2000&camera=fhaz&api_key=Hmt5EPZy7sG3ooyjwiWUh1pvVOhUVgv4chVgX5n7")
    suspend fun getCuriosityPhotos(): Response<Data>

    data class Data(
        val photos: List<Photo>
    )

    data class Photo(
        val id: Int,
        val sol: Int,
        val camera: Camera,
        @field:Json(name="img_src") val imgSrc: String,
        @field:Json(name="earth_date") val earthDate: String,
        val rover: Rover
    )

    data class Camera(
        val id: Int,
        val name: String,
        @field:Json(name="rover_id") val roverId: Int,
        @field:Json(name="full_name") val fullName: String
    )

    data class Rover(
        val id: Int,
        val name: String,
        @field:Json(name="landing_date") val landingDate: String,
        @field:Json(name="launch_date") val launchDate: String,
        val status: String
    )
}