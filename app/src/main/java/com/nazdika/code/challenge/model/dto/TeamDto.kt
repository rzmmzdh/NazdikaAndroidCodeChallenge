package com.nazdika.code.challenge.model.dto

import com.google.gson.annotations.SerializedName
import com.nazdika.code.challenge.model.Team

data class TeamDto(
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

fun TeamDto.asTeam() = Team(
    teamId = teamId,
    englishName = englishName,
    persianName = persianName,
    logo = logo,
    localizedName = localizedName
)
