package com.google.name.framework.interactors.usecases

import com.google.name.buisness.data.base.DataState
import com.google.name.buisness.interactors.usecase.GetNameGenderUseCase
import com.google.name.framework.data.repository.NameGenderRepositoryImpl
import com.google.name.framework.presentation.model.NameGenderImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNameGenderUseCaseImpl @Inject constructor(private val repository: NameGenderRepositoryImpl) :
    GetNameGenderUseCase(repository) {

    override suspend fun getNameGender(name: String): Flow<DataState<NameGenderImpl>> =
        repository.getNameGender(name)

    override suspend fun getNameGenderWithCountry(
        name: String,
        country: String
    ): Flow<DataState<NameGenderImpl>> = repository.getNameGenderWithCountry(name, country)
}