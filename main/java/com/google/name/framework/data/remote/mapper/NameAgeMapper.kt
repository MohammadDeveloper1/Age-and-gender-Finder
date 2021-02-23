package com.google.name.framework.data.remote.mapper

import com.google.name.buisness.data.base.Mapper
import com.google.name.framework.data.remote.response.NameAgeResponseImpl
import com.google.name.framework.presentation.model.NameAgeImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameAgeMapper @Inject constructor() : Mapper<NameAgeResponseImpl, NameAgeImpl> {

    override fun from(value: NameAgeResponseImpl): NameAgeImpl {
        return with(value) {
            NameAgeImpl(name, age, country)
        }
    }

    override fun to(value: NameAgeImpl): NameAgeResponseImpl {
        TODO("Not yet implemented")
    }

    override fun fromList(value: List<NameAgeResponseImpl>): List<NameAgeImpl> {
        return value.map { from(it) }
    }

    override fun toList(value: List<NameAgeImpl>): List<NameAgeResponseImpl> {
        TODO("Not yet implemented")
    }


}