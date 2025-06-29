package com.example.dynadroid.utils

sealed class AppLoadResult<out T> {
    data class Progress(val percentage: Float) : AppLoadResult<Nothing>()
    data class Success<T>(val data: T) : AppLoadResult<T>()
}