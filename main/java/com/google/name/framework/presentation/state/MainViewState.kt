package com.google.name.framework.presentation.state

sealed class MainViewState<out R> {
    data class Loaded<out T>(val data: T) : MainViewState<T>()
    data class Error(val errorMessage: String?) : MainViewState<Nothing>()
    object Loading : MainViewState<Nothing>()
}