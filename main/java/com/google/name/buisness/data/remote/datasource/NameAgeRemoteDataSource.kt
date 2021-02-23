package com.google.name.buisness.data.remote.datasource

import com.google.name.buisness.data.remote.response.NameAgeResponse
import kotlinx.coroutines.flow.Flow

abstract class NameAgeRemoteDataSource() {

    abstract suspend fun getAgeResponse(name: String): Flow<NameAgeResponse>

    abstract suspend fun getAgeWithCountryResponse(
        name: String,
        country: String
    ): Flow<NameAgeResponse>
}