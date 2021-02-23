package com.google.name.framework.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.name.buisness.data.base.DataState
import com.google.name.framework.interactors.usecases.GetPersonUseCaseImpl
import com.google.name.framework.presentation.model.NameAgeImpl
import com.google.name.framework.presentation.model.NameGenderImpl
import com.google.name.framework.presentation.model.Person
import com.google.name.framework.presentation.model.PersonFactory
import com.google.name.framework.presentation.state.MainViewEvent
import com.google.name.framework.presentation.state.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPersonUseCaseImpl: GetPersonUseCaseImpl
) : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState<Person>> = MutableLiveData()
    val viewState: LiveData<MainViewState<Person>>
        get() = _viewState


    fun setEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.GetName -> {
                getPerson(event.name)
            }
            is MainViewEvent.GetPersonWithCountry -> {
                getPersonWithCountry(event.name, event.country)
            }
        }
    }

    private fun getPerson(name: String) {
        _viewState.value = MainViewState.Loading
        val genderDataState: MutableLiveData<DataState<NameGenderImpl>> = MutableLiveData()
        val ageDataState: MutableLiveData<DataState<NameAgeImpl>> = MutableLiveData()

        viewModelScope.launch {
            getPersonUseCaseImpl.ageUseCaseImpl.getNameAge(name).onEach {
                ageDataState.value = it
                when (val state = ageDataState.value) {
                    is DataState.Success -> {
                        val nameAge = state.data
                        getPersonUseCaseImpl.genderUseCaseImpl.getNameGender(name)
                            .onEach { values ->
                                genderDataState.value = values
                                when (val state2 = genderDataState.value) {
                                    is DataState.Success -> {
                                        val nameGender = state2.data
                                        val person =
                                            PersonFactory.createPerson(nameAge, nameGender)
                                        _viewState.value = MainViewState.Loaded((person))
                                    }
                                    is DataState.Error -> {
                                        _viewState.value =
                                            MainViewState.Error(state2.errorMessage)
                                    }
                                }
                            }.launchIn(viewModelScope)
                    }
                    is DataState.Error -> {
                        _viewState.value = MainViewState.Error(state.errorMessage)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getPersonWithCountry(name: String, country: String) {
        _viewState.value = MainViewState.Loading
        val genderDataState: MutableLiveData<DataState<NameGenderImpl>> = MutableLiveData()
        val ageDataState: MutableLiveData<DataState<NameAgeImpl>> = MutableLiveData()

        viewModelScope.launch {
            getPersonUseCaseImpl.ageUseCaseImpl.getNameAgeWithCountry(name, country).onEach {
                ageDataState.value = it
                when (val state = ageDataState.value) {
                    is DataState.Success -> {
                        val nameAge = state.data
                        getPersonUseCaseImpl.genderUseCaseImpl.getNameGenderWithCountry(
                            name,
                            country
                        )
                            .onEach { values ->
                                genderDataState.value = values
                                when (val state2 = genderDataState.value) {
                                    is DataState.Success -> {
                                        val nameGender = state2.data
                                        val person = PersonFactory.createPerson(nameAge, nameGender)
                                        _viewState.value = MainViewState.Loaded((person))
                                    }
                                    is DataState.Error -> {
                                        _viewState.value = MainViewState.Error(state2.errorMessage)
                                    }
                                }
                            }.launchIn(viewModelScope)
                    }
                    is DataState.Error -> {
                        _viewState.value = MainViewState.Error(state.errorMessage)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}