package com.vinithius.weather_app_challenge.datasource.response

import java.math.BigInteger

data class BaseWeather(
    val id: Int,
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val name: String,
    val cod: Int
)
