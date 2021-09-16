package com.mmurtazaliev.marsroverphotos.repository

import com.mmurtazaliev.marsroverphotos.api.NasaApi
import retrofit2.Response
import javax.inject.Inject

class PhotoRepository(private val nasaApi: NasaApi) {

    suspend fun getPhotos(): Response<NasaApi.Data> {
        return nasaApi.getCuriosityPhotos()
    }
}