package com.nazdika.code.challenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazdika.code.challenge.data.LiveScoreRepository
import com.nazdika.code.challenge.data.datasouce.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveScoreViewModel @Inject constructor(private val liveScoreRepository: LiveScoreRepository) :
    ViewModel() {
    init {
        getTodayMatches()
    }

    private val _uiState = MutableStateFlow(LiveScoreUiState())
    val uiState: StateFlow<LiveScoreUiState> = _uiState.asStateFlow()
    private fun getTodayMatches() {
        viewModelScope.launch {
            liveScoreRepository.getTodayMatches().collectLatest { result ->
                when (result) {
                    is Result.Error -> _uiState.update { currentState ->
                        currentState.copy(
                            error = result.exception?.localizedMessage,
                            isLoading = false,
                            data = emptyList()
                        )
                    }

                    is Result.Loading -> _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = true,
                            error = null,
                            data = emptyList()
                        )
                    }

                    is Result.Success -> _uiState.update { currentState ->
                        currentState.copy(
                            data = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        }
    }
}