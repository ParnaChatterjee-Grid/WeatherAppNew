package com.example.domain.usecase

import com.example.domain.models.City
import com.example.domain.repository.CityRepository
import javax.inject.Inject

class GetCityUsecase @Inject constructor( private val cityrepository: CityRepository) {
    suspend fun invoke(cityName:String): City{
        return cityrepository.getSearchCity(cityName)
    }
}
