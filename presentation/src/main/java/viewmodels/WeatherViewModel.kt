package com.example.presentation.viewmodels

import android.annotation.SuppressLint
import android.net.http.HttpException
import android.net.http.NetworkException
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
    //  @IoDispatcher private val iodispatcher : CoroutineDispatcher
) : ViewModel() {
    private val _cityState = MutableStateFlow<ResultState<City>>(ResultState.Loading)
    val cityState: StateFlow<ResultState<City>> = _cityState

    init {
        getCity()
    }


    @SuppressLint("NewApi")
    fun getCity() {
        _cityState.value = ResultState.Loading
        //parna
        viewModelScope.launch(/*iodispatcher*/) {
            try {
                val result = getCityUseCase.invoke("London")
                if (result != null)
                    _cityState.emit(ResultState.Success(result))// Emit the updated list
            } catch (ex: HttpException) {
                _cityState.emit(ResultState.Error(exception = ex))
            } catch (ex: IOException) {
                _cityState.emit(ResultState.Error(exception = ex))
            } catch (ex: TimeoutException) {
                _cityState.emit(ResultState.Error(exception = ex))
            }
        }
    }

}