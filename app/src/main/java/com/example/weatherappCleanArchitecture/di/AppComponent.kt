package com.example.weatherappCleanArchitecture.di

import com.example.data.module.DataModule
import com.example.data.network.Retrofit
import com.example.weatherappCleanArchitecture.BaseApplication
import com.example.weatherappCleanArchitecture.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DataModule::class, Retrofit::class]
)

interface AppComponent {
    fun inject(application: BaseApplication)
    fun inject(activity: MainActivity)
}
