package com.google.name.buisness.data.remote.datasource

import com.google.name.buisness.data.remote.response.NameGenderResponse
import kotlinx.coroutines.flow.Flow

abstract class NameGenderRemoteDataSource() {

    abstract suspend fun getGenderResponse(name: String): Flow<NameGenderResponse>

    abstract suspend fun getGenderWithCountryResponse(
        name: String,
        country: String
    ): Flow<NameGenderResponse>
}