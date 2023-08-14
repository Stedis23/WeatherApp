package com.example.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.domain.utill.CoroutineNetworkExceptionHandler
import com.example.weatherapp.presentation.uistate.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
	private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

	private val _uiStateFlow = MutableStateFlow<MainState>(MainState.Initial)
	val uiState: StateFlow<MainState>
		get() = _uiStateFlow

	private val errorHandler = CoroutineNetworkExceptionHandler { errorMessage ->
		_uiStateFlow.value = MainState.Error(errorMessage ?: "Unknown error")
	}

	fun search(city: String) {
		viewModelScope.launch(errorHandler) {
			_uiStateFlow.value = MainState.Loading
			val weather = getWeatherUseCase(city)
			_uiStateFlow.value = MainState.Content(weather)
		}
	}
}