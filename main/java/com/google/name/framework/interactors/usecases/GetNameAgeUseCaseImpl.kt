package com.google.name.framework.interactors.usecases

import com.google.name.buisness.data.base.DataState
import com.google.name.buisness.domain.model.NameAge
import com.google.name.buisness.interactors.usecase.GetNameAgeUseCase
import com.google.name.framework.data.repository.NameAgeRepositoryImpl
import com.google.name.framework.presentation.model.NameAgeImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNameAgeUseCaseImpl @Inject constructor(private val repository: NameAgeRepositoryImpl) :
    GetNameAgeUseCase(repository) {

    override suspend fun getNameAge(name: String): Flow<DataState<NameAgeImpl>> =
        repository.getNameAge(name)

    override suspend fun getNameAgeWithCountry(
        name: String,
        country: String
    ): Flow<DataState<NameAgeImpl>> = repository.getNameAgeWithCountry(name, country)
}