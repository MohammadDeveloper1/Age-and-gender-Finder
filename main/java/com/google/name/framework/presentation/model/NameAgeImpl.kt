package com.google.name.framework.presentation.model

import com.google.name.buisness.domain.model.NameAge
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class NameAgeImpl
@Inject constructor(
    val name: String,
    val age: Int?,
    val country: String?
) : NameAge()