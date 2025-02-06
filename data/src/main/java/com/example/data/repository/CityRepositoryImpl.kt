package com.example.data.repository


import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.common.CustomExceptions
import com.example.data.BuildConfig
import com.example.data.network.WeatherApiService
import com.example.domain.models.City
import com.example.domain.repository.CityRepository
import com.example.common.HttpstatusCode
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService
) : CityRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getSearchCity(cityName: String): City {
        return try {
            return weatherApiService.getCity(cityName, BuildConfig.API_KEY)
        } catch (exception: NetworkException) {
            when (exception) {
                is HttpException ->
                    when (exception.code()) {
                        HttpstatusCode.NOT_FOUND  -> throw CustomExceptions.BadRequestException(exception)
                        HttpstatusCode.UNAUTHORIZED -> throw CustomExceptions.UnauthorizedException(exception)
                        HttpstatusCode.INTERNAL_SERVER_ERROR -> throw CustomExceptions.ServerErrorException(exception)
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
