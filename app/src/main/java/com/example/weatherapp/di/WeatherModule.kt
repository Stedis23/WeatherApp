package com.example.weatherapp.di

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.repository.WeatherRepositoryImp
import com.example.weatherapp.data.retrofit.RetrofitBuilder
import com.example.weatherapp.data.retrofit.createRetrofitService
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.presentation.MainViewModel
import com.example.weatherapp.ui.recyclerview.RecyclerViewAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
	viewModel {
		MainViewModel(
			getWeatherUseCase = get()
		)
	}
}

internal val RetrofitModule = module {
	factory { createRetrofitService<WeatherApi>(RetrofitBuilder.getRetrofit()) }
}

internal val UseCaseModule = module {
	factory { GetWeatherUseCase(repository = get()) }
}

internal val RepositoryModule = module {
	factory<WeatherRepository> { WeatherRepositoryImp(weatherApi = get()) }
}

internal val RecyclerViewAdapterModule = module {
	factory { RecyclerViewAdapter() }
}

val WeatherModules = listOf(
	viewModelModule,
	RetrofitModule,
	UseCaseModule,
	RepositoryModule,
	RecyclerViewAdapterModule
)