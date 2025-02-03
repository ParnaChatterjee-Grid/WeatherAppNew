package com.example.data.module


import com.example.data.network.WeatherApiService
import com.example.data.repository.CityRepositoryImpl
import com.example.domain.repository.CityRepository
import com.example.domain.usecase.GetCityUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
    @Provides
    @Singleton
    fun provideCityRepository(weatherApiService: WeatherApiService):
            CityRepository = CityRepositoryImpl(weatherApiService = weatherApiService)


    @Provides
    @Singleton
    fun provideGetSearchCityUsecase(repository: CityRepository): GetCityUsecase {
        return GetCityUsecase(repository)
    }
}
