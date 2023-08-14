package com.example.weatherapp.ui.recyclerview

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.domain.entities.ForecastDay
import com.squareup.picasso.Picasso

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	companion object {

		fun from(parent: ViewGroup): WeatherViewHolder {
			val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.wheather_item, parent, false)
			return WeatherViewHolder(layoutInflater)
		}
	}

	@SuppressLint("SetTextI18n")
	fun bind(forecastDay: ForecastDay) {
		val date = itemView.findViewById<TextView>(R.id.date)
		val condition = itemView.findViewById<TextView>(R.id.condition)
		val temperature = itemView.findViewById<TextView>(R.id.temperature)
		val windSpeed = itemView.findViewById<TextView>(R.id.windSpeed)
		val humidity = itemView.findViewById<TextView>(R.id.humidity)
		val icon = itemView.findViewById<ImageView>(R.id.icon)
		date.text = forecastDay.date
		condition.text = forecastDay.day.condition.text
		temperature.text = "${forecastDay.day.temperature} °C"
		windSpeed.text = "${forecastDay.day.windSpeed}  км/ч"
		humidity.text = forecastDay.day.humidity.toString()
		Picasso.with(itemView.context)
			.load("https:${forecastDay.day.condition.icon}".toUri())
			.into(icon)
	}
}