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

    private val _weatherLocation = MutableLiveData<BaseWeather?>()
    val weatherLocation: LiveData<BaseWeather?>
        get() = _weatherLocation

    fun getWeatherLocation(location: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.getWeatherByLocation(location)?.run {
                    _weatherLocation.postValue(this)
                }
            } catch (e: Exception) {
                Log.e("Error detail weather", e.toString())
            }
        }
    }

}