package com.nazdika.code.challenge.model

data class Match(
    val matchId: Long? = null,
    val timestamp: Long = 0,
    val homeTeamScore: Int? = null,
    val awayTeamScore: Int? = null,
    val homeTeamPen: Int? = null,
    val awayTeamPen: Int? = null,
    val matchStarted: Boolean? = null,
    val liveUrl: String? = null,
    val liveStreamUrl: String? = null,
    val hasVideo: Boolean? = null,
    val matchEnded: Boolean? = null,
    val hasLive: Boolean? = null,
    val shortDateText: String? = null,
    val hasDetails: Boolean? = null,
    val status: String? = null,
    val homeTeam: Team? = null,
    val awayTeam: Team? = null,
    override val itemType: Int = 1
) : ItemType
