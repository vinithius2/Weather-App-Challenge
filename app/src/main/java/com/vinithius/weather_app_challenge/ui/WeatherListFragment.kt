package com.vinithius.weather_app_challenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinithius.weather_app_challenge.R
import com.vinithius.weather_app_challenge.databinding.FragmentListWeatherBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WeatherListFragment : Fragment() {

    private val viewModel by sharedViewModel<WeatherAppViewModel>()
    private lateinit var binding: FragmentListWeatherBinding
    private lateinit var adapter: WeatherListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerWeatherList()
        viewModel.getListWeatherLocation()
    }

    private fun observerWeatherList() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewWeather.layoutManager = layoutManager

        viewModel.listWeatherLocation.observe(viewLifecycleOwner) { weather_location ->
            weather_location?.let {
                adapter = WeatherListAdapter(it)
                binding.recyclerViewWeather.adapter = adapter.apply {
                    onCallBackClickDetail = { id ->
                        with(viewModel) {
                            resetIdWheater()
                            setIdWheater(id)
                        }
                        findNavController().navigate(
                            R.id.action_listWeatherFragment_to_detailWeatherFragment,
                        )
                    }
                }
            }
        }

    }
}