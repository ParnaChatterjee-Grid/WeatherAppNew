package com.example.domain.repository

import com.example.domain.models.City

interface CityRepository{
    suspend fun getSearchCity(cityName:String) : City
}


