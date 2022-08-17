package com.vinithius.weather_app_challenge.ui

import android.os.Bundle
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
            binding.textLocation.text = weather_location?.name
        }
    }

}