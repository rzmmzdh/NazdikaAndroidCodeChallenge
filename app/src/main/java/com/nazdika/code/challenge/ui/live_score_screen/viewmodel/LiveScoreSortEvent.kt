package com.nazdika.code.challenge.ui.live_score_screen.viewmodel

sealed class LiveScoreSortEvent {
    object Default: LiveScoreSortEvent()
    object Time: LiveScoreSortEvent()
}
