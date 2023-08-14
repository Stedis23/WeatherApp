package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entities.Weather

interface WeatherRepository {

	suspend fun getWeather(city: String): Weather
}