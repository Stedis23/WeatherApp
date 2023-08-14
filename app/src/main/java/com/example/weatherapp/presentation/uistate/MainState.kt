package com.example.weatherapp.presentation.uistate

import com.example.weatherapp.domain.entities.Weather

sealed class MainState {

	object Initial : MainState()

	data class Content(val weather: Weather) : MainState()

	object Loading : MainState()

	data class Error(val errorMessage: String) : MainState()
}