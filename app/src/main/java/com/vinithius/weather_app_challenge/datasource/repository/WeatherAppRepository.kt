package com.vinithius.weather_app_challenge.datasource.repository

import com.vinithius.weather_app_challenge.datasource.response.BaseWeather

class WeatherAppRepository(private val remoteDataSource: WeatherAppRemoteDataSource) {

    suspend fun getWeatherByLocation(location: String): BaseWeather? {
        return try {
            with(remoteDataSource) {
                getWeatherByLocation(location)
            }
        } catch (e: Exception) {
            null
        }
    }
}
