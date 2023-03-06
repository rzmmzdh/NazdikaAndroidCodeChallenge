package com.nazdika.code.challenge.model.dto

import com.google.gson.annotations.SerializedName
import com.nazdika.code.challenge.model.SoccerMatchResult

data class SoccerMatchResultDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: SoccerMatchResultDataDto? = null
)

fun SoccerMatchResultDto.asSoccerMatchesResult() =
    SoccerMatchResult(success = success, data = data?.asSoccerMatchesData())