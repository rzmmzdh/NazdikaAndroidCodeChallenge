package com.nazdika.code.challenge.model.dto

import com.google.gson.annotations.SerializedName
import com.nazdika.code.challenge.model.SoccerMatchResultData

data class SoccerMatchResultDataDto(
    @SerializedName("competition_matches")
    val competitionMatches: List<CompetitionMatchDto> = emptyList()
)

fun SoccerMatchResultDataDto.asSoccerMatchesData() =
    SoccerMatchResultData(competitions = competitionMatches.map { it.asCompetitionMatch() })