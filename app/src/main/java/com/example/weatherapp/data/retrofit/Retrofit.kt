package com.example.weatherapp.data.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

	private const val BASE_URL = "https://api.weatherapi.com/v1/"

	fun getRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(MoshiConverterFactory.create(provideMoshi()).asLenient())
			.build()
	}

	private fun provideMoshi(): Moshi {
		return Moshi
			.Builder()
			.add(KotlinJsonAdapterFactory())
			.build()
	}
}

inline fun <reified T> createRetrofitService(retrofit: Retrofit): T =
	retrofit.create(T::class.java)