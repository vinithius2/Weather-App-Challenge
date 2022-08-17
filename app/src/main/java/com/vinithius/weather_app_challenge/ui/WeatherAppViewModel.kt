package com.vinithius.weather_app_challenge.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinithius.weather_app_challenge.datasource.repository.WeatherAppRepository
import com.vinithius.weather_app_challenge.datasource.response.BaseWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherAppViewModel(private val repository: WeatherAppRepository) : ViewModel() {

    private val _listWeatherLocation = MutableLiveData<List<BaseWeather>?>()
    val listWeatherLocation: LiveData<List<BaseWeather>?>
        get() = _listWeatherLocation

    private val _detailWeatherLocation = MutableLiveData<BaseWeather?>()
    val detailWeatherLocation: LiveData<BaseWeather?>
        get() = _detailWeatherLocation

    private var _idWheater: Int = 0
    val idWheater: Int
        get() = _idWheater

    fun setIdWheater(value: Int) {
        _idWheater = value
    }

    fun resetIdWheater() {
        _idWheater = 0
    }

    fun getListWeatherLocation() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.getWeatherByLocation()?.run {
                    _listWeatherLocation.postValue(this)
                }
            } catch (e: Exception) {
                Log.e("Error weather", e.toString())
            }
        }
    }

    fun getDetailWeatherLocation() {
        try {
            _listWeatherLocation.value?.find { it.id == _idWheater }?.run {
                _detailWeatherLocation.postValue(this)
            }
        } catch (e: Exception) {
            Log.e("Error weather", e.toString())
        }
    }


}