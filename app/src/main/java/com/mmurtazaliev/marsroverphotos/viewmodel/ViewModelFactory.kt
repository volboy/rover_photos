package com.mmurtazaliev.marsroverphotos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mmurtazaliev.marsroverphotos.MainViewModel
import com.mmurtazaliev.marsroverphotos.repository.PhotoRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val photoRepository: PhotoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(photoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}