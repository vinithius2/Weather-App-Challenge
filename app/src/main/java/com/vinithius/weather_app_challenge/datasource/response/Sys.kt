package com.vinithius.weather_app_challenge.datasource.response

data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int,
)
