package com.mmurtazaliev.marsroverphotos.di

import com.mmurtazaliev.marsroverphotos.MainActivity
import com.mmurtazaliev.marsroverphotos.repository.PhotoRepository
import com.mmurtazaliev.marsroverphotos.viewmodel.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [ViewModelModule::class])
interface MainComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainComponent

        @BindsInstance
        fun id(id: Long): Builder
    }

    fun getPhotoRepository():PhotoRepository
    fun getViewModelFactory(): ViewModelFactory
}