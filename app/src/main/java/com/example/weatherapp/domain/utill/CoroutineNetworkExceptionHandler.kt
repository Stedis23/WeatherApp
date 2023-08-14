package com.example.weatherapp.domain.utill

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

@Suppress("FunctionName")
inline fun CoroutineNetworkExceptionHandler(
	crossinline handler: (Message: String?) -> Unit,
): CoroutineExceptionHandler =
	object : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {
		override fun handleException(context: CoroutineContext, exception: Throwable) {
			handler.invoke(exception.message)
		}
	}