package com.nazdika.code.challenge.model

import com.google.gson.annotations.SerializedName

data class CompetitionMatchPojo(
    @SerializedName("competition_id")
    val competitionId: Int? = null,
    @SerializedName("name_fa")
    val persianName: String? = null,
    @SerializedName("logo")
    val logo: String? = null,
    @SerializedName("localized_name")
    val localizedName: String? = null,
    @SerializedName("matches")
    val matches: List<MatchPojo>? = null
)
