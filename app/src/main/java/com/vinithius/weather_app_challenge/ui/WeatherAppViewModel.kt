package com.vinithius.weather_app_challenge.ui

import androidx.lifecycle.ViewModel
import com.vinithius.weather_app_challenge.datasource.repository.WeatherAppRepository

class WeatherAppViewModel(private val repository: WeatherAppRepository) : ViewModel() {
}