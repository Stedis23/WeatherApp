package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.entities.Weather
import com.example.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(
	private val repository: WeatherRepository
) {

	suspend operator fun invoke(city: String): Weather = repository.getWeather(city)
}