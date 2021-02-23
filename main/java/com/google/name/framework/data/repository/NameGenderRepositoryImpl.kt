package com.google.name.framework.data.repository

import com.google.name.buisness.data.base.DataState
import com.google.name.buisness.data.repository.NameGenderRepository
import com.google.name.framework.data.remote.datasource.NameGenderRemoteDataSourceImpl
import com.google.name.framework.data.remote.mapper.NameGenderMapper
import com.google.name.framework.presentation.model.NameGenderImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameGenderRepositoryImpl
@Inject constructor(
    private val genderRemoteDataSource: NameGenderRemoteDataSourceImpl,
    private val genderMapper: NameGenderMapper
) : NameGenderRepository(genderRemoteDataSource) {

    override suspend fun getNameGender(name: String): Flow<DataState<NameGenderImpl>> = flow {
        genderRemoteDataSource.getGenderResponse(name).catch { exception ->
            emit(DataState.Error(exception.message))
        }.collect { data ->
            emit(DataState.Success(genderMapper.from(data)))
        }
    }

    override suspend fun getNameGenderWithCountry(
        name: String,
        country: String
    ): Flow<DataState<NameGenderImpl>> = flow {
        genderRemoteDataSource.getGenderWithCountryResponse(name, country).catch { exception ->
            emit(DataState.Error(exception.message))
        }.collect { data ->
            emit(DataState.Success(genderMapper.from(data)))
        }
    }
}