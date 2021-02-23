package com.google.name.framework.interactors.usecases

import com.google.name.buisness.interactors.usecase.GetPersonUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class GetPersonUseCaseImpl @Inject constructor(
    val ageUseCaseImpl: GetNameAgeUseCaseImpl,
    val genderUseCaseImpl: GetNameGenderUseCaseImpl
) : GetPersonUseCase(ageUseCaseImpl, genderUseCaseImpl)