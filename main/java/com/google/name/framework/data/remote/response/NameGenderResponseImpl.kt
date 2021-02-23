package com.google.name.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import com.google.name.buisness.data.remote.response.NameGenderResponse

data class NameGenderResponseImpl(
    val name: String,
    val gender: String?,
    val probability: Double?,
    val count: Int?,
    @SerializedName("country_id")
    val country: String?
) : NameGenderResponse()