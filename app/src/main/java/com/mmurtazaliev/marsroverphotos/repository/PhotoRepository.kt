package com.mmurtazaliev.marsroverphotos.repository

import com.mmurtazaliev.marsroverphotos.api.NasaApi
import com.mmurtazaliev.marsroverphotos.di.MainScope
import retrofit2.Response
import javax.inject.Inject

@MainScope
class PhotoRepository @Inject constructor(private val nasaApi: NasaApi, private val id: Long) {

    suspend fun getPhotos(): Response<NasaApi.Data> {
        return nasaApi.getCuriosityPhotos()
    }
}