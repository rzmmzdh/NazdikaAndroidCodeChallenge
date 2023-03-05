package com.nazdika.code.challenge.model.dto

import com.google.gson.annotations.SerializedName
import com.nazdika.code.challenge.model.CompetitionMatch
import com.nazdika.code.challenge.model.SoccerMatchResultData

data class SoccerMatchesResultDataDto(
    @SerializedName("competition_matches")
    val competitionMatches: List<CompetitionMatchDto?>? = null
)

fun SoccerMatchesResultDataDto.asSoccerMatchesData() =
    SoccerMatchResultData(competitionMatches = competitionMatches?.map {
        it?.asCompetitionMatch()
            ?: CompetitionMatch()
    }
        ?: emptyList())