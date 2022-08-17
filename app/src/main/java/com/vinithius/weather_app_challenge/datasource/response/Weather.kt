package com.vinithius.weather_app_challenge.datasource.response

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)
