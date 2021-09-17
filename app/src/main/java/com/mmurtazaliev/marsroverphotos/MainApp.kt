package com.mmurtazaliev.marsroverphotos

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import com.mmurtazaliev.marsroverphotos.api.NasaApi
import com.mmurtazaliev.marsroverphotos.di.AppComponent
import com.mmurtazaliev.marsroverphotos.di.AppModule
import com.mmurtazaliev.marsroverphotos.di.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(this)
            .buildAppComponent()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> applicationContext.appComponent
    }