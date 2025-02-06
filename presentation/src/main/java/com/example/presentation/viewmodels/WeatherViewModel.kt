package com.example.presentation.viewmodels

import android.annotation.SuppressLint
import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.ResultState
import com.example.domain.models.City
import com.example.domain.usecase.GetCityUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getCityUseCase: GetCityUsecase
) : ViewModel() {
    private val _cityState = MutableStateFlow<ResultState<City>>(ResultState.Loading)
    val cityState: StateFlow<ResultState<City>> = _cityState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCity(city: String) {
        _cityState.value = ResultState.Loading
        viewModelScope.launch() {
            try {
                val result = getCityUseCase.invoke(city)
                if (result != null)
                    _cityState.emit(ResultState.Success(result))// Emit the weather information
            } catch (ex: NetworkException) {
                _cityState.emit(ResultState.Error(exception = ex))
            } catch (ex: IOException) {
                _cityState.emit(ResultState.Error(exception = ex))
            } catch (ex: TimeoutException) {
                _cityState.emit(ResultState.Error(exception = ex))
            }catch (ex: retrofit2.HttpException) {
                _cityState.emit(ResultState.Error(exception = ex))
            }
        }
    }
}
