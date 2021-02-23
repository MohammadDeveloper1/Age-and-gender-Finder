package com.google.name.buisness.data.repository

import com.google.name.buisness.data.base.DataState
import com.google.name.buisness.data.remote.datasource.NameGenderRemoteDataSource
import com.google.name.buisness.domain.model.NameGender
import kotlinx.coroutines.flow.Flow

abstract class NameGenderRepository(private val genderRemoteDataSource: NameGenderRemoteDataSource) {

    abstract suspend fun getNameGender(name: String): Flow<DataState<NameGender>>

    abstract suspend fun getNameGenderWithCountry(
        name: String,
        country: String
    ): Flow<DataState<NameGender>>
}