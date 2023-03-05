package com.nazdika.code.challenge.ui.viewmodel

import com.nazdika.code.challenge.model.CompetitionMatch

data class LiveScoreUiState(
    val data: List<CompetitionMatch?>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
