package com.google.name.buisness.data.repository

import com.google.name.buisness.data.base.DataState
import com.google.name.buisness.data.remote.datasource.NameAgeRemoteDataSource
import com.google.name.buisness.domain.model.NameAge
import kotlinx.coroutines.flow.Flow

abstract class NameAgeRepository(private val ageDataSource: NameAgeRemoteDataSource) {

    abstract suspend fun getNameAge(name: String): Flow<DataState<NameAge>>

    abstract suspend fun getNameAgeWithCountry(
        name: String,
        country: String
    ): Flow<DataState<NameAge>>
}