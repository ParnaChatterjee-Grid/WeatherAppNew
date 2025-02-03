package com.example.data.network

import com.example.domain.models.City
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService{
    @GET("weather")
    suspend fun getCity(
        @Query("q") city: String,
        @Query("appid") appId: String
    ): City
}