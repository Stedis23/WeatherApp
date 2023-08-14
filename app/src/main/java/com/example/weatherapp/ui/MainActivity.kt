package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.presentation.MainViewModel
import com.example.weatherapp.presentation.uistate.MainState
import com.example.weatherapp.ui.recyclerview.RecyclerViewAdapter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

	private val viewModel: MainViewModel by viewModel()
	private val adapter: RecyclerViewAdapter by inject()
	private lateinit var loadingBar: ProgressBar
	private lateinit var errorMessage: TextView
	private lateinit var recyclerView: RecyclerView

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setupViews()
		viewModel.uiStateFlow.onEach(::renderUIState).launchIn(lifecycleScope)
	}

	private fun setupViews() {
		val searchButton = findViewById<ImageView>(R.id.search)
		val searchText = findViewById<EditText>(R.id.searchEditText)
		searchButton.setOnClickListener { viewModel.search(searchText.text.toString()) }

		loadingBar = findViewById(R.id.loadingBar)
		errorMessage = findViewById(R.id.errorMessage)
		recyclerView = findViewById(R.id.recyclerView)

		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(this)

		viewModel.uiStateFlow
			.filterIsInstance<MainState.Content>()
			.onEach {
				adapter.update(it.weather.forecast.forecastDayList)
			}.launchIn(lifecycleScope)
	}

	private fun renderUIState(state: MainState) = when (state) {
		is MainState.Content -> renderContent()
		is MainState.Error   -> renderError(state.errorMessage)
		is MainState.Initial -> renderInitial()
		is MainState.Loading -> renderLoading()
	}

	private fun renderContent() {
		loadingBar.isVisible = false
		errorMessage.isVisible = false
		recyclerView.isVisible = true
	}

	private fun renderError(error: String) {
		loadingBar.isVisible = false
		errorMessage.isVisible = true
		recyclerView.isVisible = false
		if (error == NOTHING_FOUND_ERROR) {
			errorMessage.text = getString(R.string.nothing_found_error)
		} else {
			errorMessage.text = getString(R.string.unknown_error)
		}
	}

	private fun renderInitial() {
		loadingBar.isVisible = false
		errorMessage.isVisible = false
		recyclerView.isVisible = false
	}

	private fun renderLoading() {
		loadingBar.isVisible = true
		errorMessage.isVisible = false
		recyclerView.isVisible = false
	}

	companion object {

		private const val NOTHING_FOUND_ERROR = "HTTP 400 "
	}
}