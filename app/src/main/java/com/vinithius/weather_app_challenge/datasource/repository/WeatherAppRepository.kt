package com.vinithius.weather_app_challenge.datasource.repository

import com.vinithius.weather_app_challenge.datasource.response.BaseWeather

class WeatherAppRepository(private val remoteDataSource: WeatherAppRemoteDataSource) {

    suspend fun getWeatherByLocation(): List<BaseWeather>? {
        return try {
            val defaultLocationWeather = mutableListOf<BaseWeather>()
            val defaultLocationNames = listOf(
                "Lisbon",
                "Madrid",
                "Paris",
                "Berlin",
                "Copenhagen",
                "Rome",
                "London",
                "Dublin",
                "Prague",
                "Vienna"
            )
            with(remoteDataSource) {
                defaultLocationNames.forEach { location ->
                    val weather = getWeatherByLocation(location)
                    defaultLocationWeather.add(weather)
                }
            }
            defaultLocationWeather.toList()
        } catch (e: Exception) {
            null
        }
    }
}
