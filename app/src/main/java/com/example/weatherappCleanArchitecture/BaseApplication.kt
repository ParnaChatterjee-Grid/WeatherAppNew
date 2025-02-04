package com.example.weatherappCleanArchitecture

import android.app.Application
import com.example.weatherappCleanArchitecture.di.DaggerAppComponent
import com.example.weatherappCleanArchitecture.di.AppComponent

class BaseApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
    }
}
