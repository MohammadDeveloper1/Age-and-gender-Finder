package com.google.name.framework.presentation.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class Person @Inject constructor(
    val name: String,
    val age: Int?,
    val gender: String?,
    val country: String?
)