package com.vinithius.weather_app_challenge.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vinithius.weather_app_challenge.databinding.FragmentDetailWeatherBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WeatherDetailFragment : Fragment() {

    private val viewModel by sharedViewModel<WeatherAppViewModel>()
    private lateinit var binding: FragmentDetailWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerWeatherDetail()
        viewModel.getDetailWeatherLocation()
    }

    private fun observerWeatherDetail() {
        viewModel.detailWeatherLocation.observe(viewLifecycleOwner) { weather_location ->
            weather_location?.let {
                try {
                    with(binding) {
                        textLocation.text = it.name
                        textDegData.text = it.wind.deg.toString()
                        textSpeedData.text = it.wind.speed.toString()
                        textGustData.text = it.wind.gust.toString()
                        val humidity = "${it.main.humidity}%"
                        textHumidityData.text = humidity
                        textPressureData.text = it.main.pressure.toString()
                        textFeelsLikeData.text =
                            String.format("%.0fº", it.main.feels_like)
                        textTempData.text = String.format("%.0fº", it.main.temp)
                        textTempMaxData.text = String.format("%.0fº", it.main.temp_max)
                        textTempMinData.text = String.format("%.0fº", it.main.temp_min)
                    }
                } catch (e: Exception) {
                    Log.e("observerWeatherDetail", e.toString())
                }
            }
        }
    }

}