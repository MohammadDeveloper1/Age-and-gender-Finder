package com.google.name.buisness.interactors.usecase

abstract class GetPersonUseCase(
    val ageUseCase: GetNameAgeUseCase,
    val genderUseCase: GetNameGenderUseCase
)

