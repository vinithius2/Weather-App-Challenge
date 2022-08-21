package com.vinithius.weather_app_challenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinithius.weather_app_challenge.databinding.WeatherViewHolderBinding
import com.vinithius.weather_app_challenge.datasource.response.BaseWeather
import com.vinithius.weather_app_challenge.extensions.getDayWeekName
import com.vinithius.weather_app_challenge.extensions.getIco
import java.util.*

class WeatherListAdapter(
    private val dataSet: List<BaseWeather>
) : RecyclerView.Adapter<WeatherListAdapter.WeatherListViewHolder>() {

    var onCallBackClickDetail: ((id: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val binding =
            WeatherViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    inner class WeatherListViewHolder(
        private val binding: WeatherViewHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: BaseWeather) {
            with(binding) {
                textLocation.text = weather.name
                textDegrees.text = String.format("%.0f", weather.main.temp)
                val humidity = "${weather.main.humidity}%"
                textHumidity.text = humidity
                textWeatherName.text = weather.weather.first().main
                textIco.text = weather.weather.first().icon.getIco()

                val calendar = Calendar.getInstance()
                calendar.time = Date(weather.dt)
                textDayNumber.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
                textDayName.text = calendar.get(Calendar.DAY_OF_WEEK).getDayWeekName()

                root.setOnClickListener {
                    onCallBackClickDetail?.invoke(weather.id)
                }
            }
        }

    }
}
