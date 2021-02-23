package com.google.name.buisness.data.base

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val errorMessage: String?) : DataState<Nothing>()
}
