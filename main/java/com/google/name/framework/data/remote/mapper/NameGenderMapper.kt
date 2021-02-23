package com.google.name.framework.data.remote.mapper

import com.google.name.buisness.data.base.Mapper
import com.google.name.framework.data.remote.response.NameGenderResponseImpl
import com.google.name.framework.presentation.model.NameGenderImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameGenderMapper @Inject constructor() : Mapper<NameGenderResponseImpl, NameGenderImpl> {
    override fun from(value: NameGenderResponseImpl): NameGenderImpl {
        return with(value) {
            NameGenderImpl(name, gender, country)
        }
    }

    override fun to(value: NameGenderImpl): NameGenderResponseImpl {
        TODO("Not yet implemented")
    }

    override fun fromList(value: List<NameGenderResponseImpl>): List<NameGenderImpl> {
        return value.map { from(it) }
    }

    override fun toList(value: List<NameGenderImpl>): List<NameGenderResponseImpl> {
        TODO("Not yet implemented")
    }
}