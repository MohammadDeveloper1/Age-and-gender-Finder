package com.google.name.framework.data.remote.response

import com.google.gson.annotations.SerializedName
import com.google.name.buisness.data.remote.response.NameAgeResponse

data class NameAgeResponseImpl(
    val name: String,
    val age: Int?,
    val count: Int?,
    @SerializedName("country_id")
    val country: String?
) : NameAgeResponse()