package com.nazdika.code.challenge.ui.live_score_screen.viewmodel

import com.nazdika.code.challenge.model.CompetitionMatch

data class LiveScoreUiState(
    val data: List<CompetitionMatch> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
