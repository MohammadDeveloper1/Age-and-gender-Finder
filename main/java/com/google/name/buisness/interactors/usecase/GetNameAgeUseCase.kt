package com.google.name.buisness.interactors.usecase

import com.google.name.buisness.data.base.DataState
import com.google.name.buisness.data.repository.NameAgeRepository
import com.google.name.buisness.domain.model.NameAge
import kotlinx.coroutines.flow.Flow

abstract class GetNameAgeUseCase(private val repository: NameAgeRepository) {

    abstract suspend fun getNameAge(name: String): Flow<DataState<NameAge>>

    abstract suspend fun getNameAgeWithCountry(
        name: String,
        country: String
    ): Flow<DataState<NameAge>>
}