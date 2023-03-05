package com.nazdika.code.challenge.model

import com.google.gson.annotations.SerializedName

data class SoccerMatchesResultPojo(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: SoccerMatchesResultDataPojo? = null
)