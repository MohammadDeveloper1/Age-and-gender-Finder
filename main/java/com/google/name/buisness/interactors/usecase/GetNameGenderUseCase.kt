package com.google.name.buisness.interactors.usecase

import com.google.name.buisness.data.base.DataState
import com.google.name.buisness.data.repository.NameGenderRepository
import com.google.name.buisness.domain.model.NameGender
import kotlinx.coroutines.flow.Flow

abstract class GetNameGenderUseCase(private val repository: NameGenderRepository) {

    abstract suspend fun getNameGender(name: String): Flow<DataState<NameGender>>

    abstract suspend fun getNameGenderWithCountry(
        name: String,
        country: String
    ): Flow<DataState<NameGender>>
}