package com.google.name.framework.data.remote.service.retrofit

import com.google.name.framework.data.remote.response.NameGenderResponseImpl
import retrofit2.http.GET
import retrofit2.http.Query

interface NameGenderApi {

    @GET(".")
    suspend fun getGenderResponse(@Query("name") name: String): NameGenderResponseImpl

    @GET(".")
    suspend fun getGenderWithCountryResponse(
        @Query("name") name: String,
        @Query("country_id") country: String
    ): NameGenderResponseImpl
}