package com.google.name.framework.presentation.state

sealed class MainViewEvent {

    data class GetName(val name: String) : MainViewEvent()
    data class GetPersonWithCountry(val name: String, val country: String) : MainViewEvent()
}