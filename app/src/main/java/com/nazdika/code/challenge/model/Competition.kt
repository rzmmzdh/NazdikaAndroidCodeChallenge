package com.nazdika.code.challenge.model

data class Competition(
    val competitionId: Int? = null,
    val persianName: String? = null,
    val logo: String? = null,
    val localizedName: String? = null,
    val matches: List<Match>? = emptyList(),
    override val itemType: Int = 0
) : ItemType

