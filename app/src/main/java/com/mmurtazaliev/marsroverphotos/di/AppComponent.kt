package com.mmurtazaliev.marsroverphotos.di

import com.mmurtazaliev.marsroverphotos.MainActivity
import com.mmurtazaliev.marsroverphotos.viewmodel.DatabaseHelper
import com.mmurtazaliev.marsroverphotos.viewmodel.NetworkUtils
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    //fun getDatabaseHelper(): DatabaseHelper
    //fun getNetworkUtils(): NetworkUtils
    fun injectMainActivity(mainActivity: MainActivity)
}

