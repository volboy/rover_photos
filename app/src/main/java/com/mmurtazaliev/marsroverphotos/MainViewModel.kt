package com.mmurtazaliev.marsroverphotos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmurtazaliev.marsroverphotos.api.NasaApi
import com.mmurtazaliev.marsroverphotos.repository.PhotoRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel(photoRepository: PhotoRepository) : ViewModel() {

    var photos = MutableLiveData<List<NasaApi.Photo>>()
    var errors = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            try {
                val response = photoRepository.getPhotos()
                if (response.isSuccessful) {
                    photos.value = response.body()?.photos
                } else {
                    errors.value = response.errorBody().toString()
                }
            } catch (e: Exception) {
                Log.i("NASA_API", "ERROR = $e")
            }
        }
    }
}