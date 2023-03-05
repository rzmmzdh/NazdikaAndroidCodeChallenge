package com.nazdika.code.challenge.model.dto

import com.google.gson.annotations.SerializedName
import com.nazdika.code.challenge.model.SoccerMatchResult

data class SoccerMatchesResultDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: SoccerMatchesResultDataDto? = null
)

fun SoccerMatchesResultDto.asSoccerMatchesResult() =
    SoccerMatchResult(success = success, data = data?.asSoccerMatchesData())