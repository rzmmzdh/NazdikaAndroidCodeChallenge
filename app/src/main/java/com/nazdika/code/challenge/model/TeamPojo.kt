package com.nazdika.code.challenge.model

import com.google.gson.annotations.SerializedName

data class TeamPojo(
    @SerializedName("team_id")
    val teamId: Long? = null,
    @SerializedName("name_en")
    val englishName: String? = null,
    @SerializedName("name_fa")
    val persianName: String? = null,
    @SerializedName("logo")
    val logo: String? = null,
    @SerializedName("localized_name")
    val localizedName: String? = null
)
