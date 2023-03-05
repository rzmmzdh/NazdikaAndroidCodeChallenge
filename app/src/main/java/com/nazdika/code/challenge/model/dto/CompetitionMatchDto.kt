package com.nazdika.code.challenge.model.dto

import com.google.gson.annotations.SerializedName
import com.nazdika.code.challenge.model.CompetitionMatch

data class CompetitionMatchDto(
    @SerializedName("competition_id")
    val competitionId: Int? = null,
    @SerializedName("name_fa")
    val persianName: String? = null,
    @SerializedName("logo")
    val logo: String? = null,
    @SerializedName("localized_name")
    val localizedName: String? = null,
    @SerializedName("matches")
    val matches: List<MatchDto> = emptyList()
)

fun CompetitionMatchDto.asCompetitionMatch() =
    CompetitionMatch(
        competitionId = competitionId,
        persianName = persianName,
        logo = logo,
        localizedName = localizedName,
        matches = matches.map { it.asMatch() }
    )