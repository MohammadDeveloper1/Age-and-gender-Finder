package com.google.name.framework.data.remote.service.retrofit

import com.google.name.framework.data.remote.response.NameAgeResponseImpl
import retrofit2.http.GET
import retrofit2.http.Query

interface NameAgeApi {

    @GET(".")
    suspend fun getAgeResponse(@Query("name") name: String): NameAgeResponseImpl

    @GET(".")
    suspend fun getAgeWithCountryResponse(
        @Query("name") name: String,
        @Query("country_id") country: String
    ): NameAgeResponseImpl
}