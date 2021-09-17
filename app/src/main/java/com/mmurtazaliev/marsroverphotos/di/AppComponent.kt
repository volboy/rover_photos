package com.mmurtazaliev.marsroverphotos.di

import android.content.Context
import com.mmurtazaliev.marsroverphotos.MainActivity
import com.mmurtazaliev.marsroverphotos.viewmodel.DatabaseHelper
import com.mmurtazaliev.marsroverphotos.viewmodel.NetworkUtils
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun getMainComponent():MainComponent

    @Component.Builder
    interface MainAppBuilder {
        fun buildAppComponent(): AppComponent

        @BindsInstance
        fun context(context: Context):MainAppBuilder
    }
}

