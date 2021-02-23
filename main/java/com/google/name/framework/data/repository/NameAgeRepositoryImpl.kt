package com.google.name.framework.data.repository

import com.google.name.buisness.data.base.DataState
import com.google.name.buisness.data.repository.NameAgeRepository
import com.google.name.framework.data.remote.datasource.NameAgeRemoteDataSourceImpl
import com.google.name.framework.data.remote.mapper.NameAgeMapper
import com.google.name.framework.presentation.model.NameAgeImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameAgeRepositoryImpl
@Inject constructor(
    private val ageRemoteDataSource: NameAgeRemoteDataSourceImpl,
    private val ageMapper: NameAgeMapper,
) : NameAgeRepository(ageRemoteDataSource) {

    override suspend fun getNameAge(name: String): Flow<DataState<NameAgeImpl>> = flow {
        ageRemoteDataSource.getAgeResponse(name).catch { exception ->
            emit(DataState.Error(exception.message))
        }.collect { data ->
            emit(DataState.Success(ageMapper.from(data)))
        }
    }

    override suspend fun getNameAgeWithCountry(
        name: String,
        country: String
    ): Flow<DataState<NameAgeImpl>> = flow {
        ageRemoteDataSource.getAgeWithCountryResponse(name, country).catch { exception ->
            emit(DataState.Error(exception.message))
        }.collect { data ->
            emit(DataState.Success(ageMapper.from(data)))
        }
    }
}