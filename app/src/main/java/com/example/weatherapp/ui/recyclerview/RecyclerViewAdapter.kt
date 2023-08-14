package com.example.weatherapp.ui.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.domain.entities.ForecastDay

class RecyclerViewAdapter : RecyclerView.Adapter<WeatherViewHolder>() {

	private var forecastDayList: List<ForecastDay> = listOf()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder = WeatherViewHolder.from(parent)

	override fun getItemCount(): Int = forecastDayList.size

	override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) = holder.bind(forecastDayList[position])

	@SuppressLint("NotifyDataSetChanged")
	fun update(forecastDayList: List<ForecastDay>) {
		this.forecastDayList = forecastDayList
		notifyDataSetChanged()
	}
}