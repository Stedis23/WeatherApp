package com.example.weatherapp.domain.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
	@Json(name = "forecast") val forecast: Forecast
)

@JsonClass(generateAdapter = true)
data class Forecast(
	@Json(name = "forecastday") val forecastDayList: List<ForecastDay>
)

@JsonClass(generateAdapter = true)
data class ForecastDay(
	@Json(name = "date") val date: String,
	@Json(name = "day") val day: Day
)

@JsonClass(generateAdapter = true)
data class Day(
	@Json(name = "avghumidity") val humidity: Int,
	@Json(name = "maxwind_kph") val windSpeed: Double,
	@Json(name = "avgtemp_c") val temperature: Double,
	@Json(name = "condition") val condition: Condition
)

@JsonClass(generateAdapter = true)
data class Condition(
	@Json(name = "text") val text: String,
	@Json(name = "icon") val icon: String,
	@Json(name = "code") val code: Int
)