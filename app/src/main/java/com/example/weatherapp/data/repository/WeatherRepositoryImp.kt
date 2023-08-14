package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.domain.entities.Weather
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImp(private val weatherApi: WeatherApi) : WeatherRepository {

	override suspend fun getWeather(city: String): Weather = weatherApi.getWeather(city)
}