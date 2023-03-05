package com.nazdika.code.challenge.model

import com.google.gson.annotations.SerializedName
import com.nazdika.code.challenge.MainActivity

data class MatchPojo(
    @SerializedName("match_id")
    val matchId: Long? = null,
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
    val homeTeam: TeamPojo? = null,
    @SerializedName("away_team")
    val awayTeam: TeamPojo? = null
)

