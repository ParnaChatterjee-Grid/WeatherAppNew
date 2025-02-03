package com.example.data.repository


import com.example.common.CustomExceptions
import com.example.data.BuildConfig
import com.example.data.network.WeatherApiService
import com.example.domain.models.City
import com.example.domain.repository.CityRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService
) : CityRepository {
    override suspend fun getSearchCity(cityName: String): City {
        return try {
            return weatherApiService.getCity(cityName, BuildConfig.API_KEY)
        } catch (exception: Exception) {
            when (exception) {
                is HttpException ->
                    when (exception.code()) {
                        404 -> throw CustomExceptions.BadRequestException(exception)
                        401 -> throw CustomExceptions.UnauthorizedException(exception)
                        500 -> throw CustomExceptions.ServerErrorException(exception)
                        else -> throw CustomExceptions.UnknownNetworkException(exception)
                    }

                is IOException -> {
                    // This could be a network timeout or no internet connection
                    throw CustomExceptions.TimeoutException(exception)
                }

                else -> {
                    throw CustomExceptions.UnknownException(exception)
                }
            }
        }
    }
}
