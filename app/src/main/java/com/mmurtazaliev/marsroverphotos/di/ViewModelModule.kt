package com.mmurtazaliev.marsroverphotos.di

import androidx.lifecycle.ViewModel
import com.mmurtazaliev.marsroverphotos.MainViewModel
import com.mmurtazaliev.marsroverphotos.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Provides
    fun provideMainViewModel(photoRepository: PhotoRepository): ViewModel {
        return MainViewModel(photoRepository)
    }
}