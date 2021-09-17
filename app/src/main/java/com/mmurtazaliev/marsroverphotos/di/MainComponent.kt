package com.mmurtazaliev.marsroverphotos.di

import com.mmurtazaliev.marsroverphotos.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [AppModule::class])
interface MainComponent {

    fun injectMainActivity(mainActivity: MainActivity)
}