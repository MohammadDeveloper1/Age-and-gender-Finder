package com.google.name.framework.data.remote.datasource

import com.google.name.buisness.data.remote.datasource.NameAgeRemoteDataSource
import com.google.name.framework.data.remote.response.NameAgeResponseImpl
import com.google.name.framework.data.remote.service.retrofit.NameAgeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameAgeRemoteDataSourceImpl @Inject constructor(private val ageApi: NameAgeApi) :
    NameAgeRemoteDataSource() {

    override suspend fun getAgeResponse(name: String): Flow<NameAgeResponseImpl> = flow {
        val response = ageApi.getAgeResponse(name)
        emit(response)
    }

    override suspend fun getAgeWithCountryResponse(
        name: String,
        country: String
    ): Flow<NameAgeResponseImpl> = flow {
        val response = ageApi.getAgeWithCountryResponse(name, country)
        emit(response)
    }
}