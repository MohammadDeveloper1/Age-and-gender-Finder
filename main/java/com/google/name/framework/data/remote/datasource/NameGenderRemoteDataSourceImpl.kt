package com.google.name.framework.data.remote.datasource

import com.google.name.buisness.data.remote.datasource.NameGenderRemoteDataSource
import com.google.name.framework.data.remote.response.NameGenderResponseImpl
import com.google.name.framework.data.remote.service.retrofit.NameGenderApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameGenderRemoteDataSourceImpl @Inject constructor(private val genderApi: NameGenderApi) :
    NameGenderRemoteDataSource() {

    override suspend fun getGenderResponse(name: String): Flow<NameGenderResponseImpl> =
        flow {
            val response = genderApi.getGenderResponse(name)
            emit(response)
        }

    override suspend fun getGenderWithCountryResponse(
        name: String,
        country: String
    ): Flow<NameGenderResponseImpl> = flow {
        val response = genderApi.getGenderWithCountryResponse(name, country)
        emit(response)
    }
}