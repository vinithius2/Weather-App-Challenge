package com.vinithius.weather_app_challenge.extensions

fun String.getIco(): String {
    return if (this.contains("d")) {
        getIcoDay(this)
    } else {
        getIcoNight(this)
    }

}

private fun getIcoDay(icon: String): String {
    return when (icon) {
        "01d" -> "1"
        "02d" -> "2"
        "03d" -> "A"
        "04d" -> "3"
        "09d" -> "K"
        "10d" -> "J"
        "11d" -> "S"
        "13d" -> "I"
        "50d" -> "Z"
        else -> ""
    }
}

private fun getIcoNight(icon: String): String {
    return when (icon) {
        "01n" -> "1"
        "02n" -> "2"
        "03n" -> "a"
        "04n" -> "3"
        "09n" -> "k"
        "10n" -> "j"
        "11n" -> "s"
        "13n" -> "i"
        "50n" -> "z"
        else -> ""
    }
}
