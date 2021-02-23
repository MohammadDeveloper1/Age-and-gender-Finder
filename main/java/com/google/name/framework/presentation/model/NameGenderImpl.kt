package com.google.name.framework.presentation.model

import com.google.name.buisness.domain.model.NameGender
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class NameGenderImpl
@Inject constructor(
    val name: String,
    val gender: String?,
    val country: String?
) : NameGender()