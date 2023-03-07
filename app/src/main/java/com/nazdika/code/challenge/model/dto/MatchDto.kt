package com.nazdika.code.challenge.model.dto

import com.google.gson.annotations.SerializedName
import com.nazdika.code.challenge.model.Match

data class MatchDto(
    @SerializedName("match_id")
    val matchId: Long? = null,
    @SerializedName("timestamp")
    val timestamp: Long = 0,
    @SerializedName("home_team_score")
    val homeTeamScore: Int? = null,
    @SerializedName("away_team_score")
    val awayTeamScore: Int? = null,
    @SerializedName("home_team_pen")
    val homeTeamPen: Int? = null,
    @SerializedName("away_team_pen")
    val awayTeamPen: Int? = null,
    @SerializedName("match_started")
    val matchStarted: Boolean? = null,
    @SerializedName("live_url")
    val liveUrl: String? = null,
    @SerializedName("live_stream_url")
    val liveStreamUrl: String? = null,
    @SerializedName("has_video")
    val hasVideo: Boolean? = null,
    @SerializedName("match_ended")
    val matchEnded: Boolean? = null,
    @SerializedName("has_live")
    val hasLive: Boolean? = null,
    @SerializedName("short_date_text")
    val shortDateText: String? = null,
    @SerializedName("has_details")
    val hasDetails: Boolean? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("home_team")
    val homeTeam: TeamDto? = null,
    @SerializedName("away_team")
    val awayTeam: TeamDto? = null
)

fun MatchDto.asMatch() = Match(
    matchId = matchId,
    timestamp = timestamp,
    homeTeamScore = homeTeamScore,
    awayTeamScore = awayTeamScore,
    homeTeamPen = homeTeamPen,
    awayTeamPen = awayTeamPen,
    matchStarted = matchStarted,
    liveUrl = liveUrl,
    liveStreamUrl = liveStreamUrl,
    hasVideo = hasVideo,
    matchEnded = matchEnded,
    hasLive = hasLive,
    shortDateText = shortDateText,
    hasDetails = hasDetails,
    status = status,
    homeTeam = homeTeam?.asTeam(),
    awayTeam = awayTeam?.asTeam()
)

