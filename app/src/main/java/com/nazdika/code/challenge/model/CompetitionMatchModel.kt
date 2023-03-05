package com.nazdika.code.challenge.model

import com.nazdika.code.challenge.MainActivity

data class CompetitionMatchModel(
    val competitionId: Int? = null,
    val persianName: String? = null,
    val logo: String? = null,
    val localizedName: String? = null,
    override val itemType: Int = 0
) : MainActivity.ItemType

