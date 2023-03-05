package com.nazdika.code.challenge.model

import com.google.gson.annotations.SerializedName

data class SoccerMatchesResultDataPojo(
    @SerializedName("competition_matches")
    val competitionMatches: List<CompetitionMatchPojo?>? = null
)