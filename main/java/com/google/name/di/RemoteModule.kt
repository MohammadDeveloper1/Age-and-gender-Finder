package com.google.name.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.name.framework.data.remote.service.retrofit.NameAgeApi
import com.google.name.framework.data.remote.service.retrofit.NameGenderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    const val AGE_BASE_URL = "https://api.agify.io/"
    const val GENDER_BASE_URL = "https://api.genderize.io/"

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideNameAgeApi(retrofitBuilder: Retrofit.Builder): NameAgeApi {
        return retrofitBuilder.baseUrl(AGE_BASE_URL).build().create(NameAgeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNameGenderApi(retrofitBuilder: Retrofit.Builder): NameGenderApi {
        return retrofitBuilder.baseUrl(GENDER_BASE_URL).build()
            .create(NameGenderApi::class.java)
    }
}