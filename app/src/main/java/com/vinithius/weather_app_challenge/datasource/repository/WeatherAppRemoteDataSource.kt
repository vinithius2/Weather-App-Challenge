package com.vinithius.weather_app_challenge.datasource.repository

import com.vinithius.weather_app_challenge.datasource.response.BaseWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAppRemoteDataSource {

    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("q") location: String,
    ): BaseWeather

}
