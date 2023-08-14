package com.example.weatherapp.data.api

import com.example.weatherapp.domain.entities.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

	@GET("forecast.json")
	suspend fun getWeather(
		@Query("q") city: String,
		@Query("days") days: Int = 5,
		@Query("key") key: String = "df5d12c6e9154ca297a104026231308"
	): Weather
}