package com.xphonesoftware.fetch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xphonesoftware.fetch.data.CandidateRepository
import com.xphonesoftware.fetch.presentation.models.CandidateUi
import com.xphonesoftware.fetch.presentation.models.toCandidateUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidateListViewModel @Inject constructor(
    private val candidateDataSource: CandidateRepository
): ViewModel() {
    private val _state = MutableStateFlow(CandidateListState())
    val state = _state.asStateFlow()

    private fun load() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(3_000) // FIXME - remove, just so spinner displays
            val candidates = candidateDataSource.getCandidates()
            _state.update {
                it.copy(
                    isLoading = false,
                    candidates = candidates.map { candidate -> candidate.toCandidateUi() }
                )
            }
        }
    }

    init {
        load()
    }

    fun onAction(action: CandidateListAction) {
        when (action) {
            is CandidateListAction.OnItemClicked -> {
                selectCandidate(candidateUi = action.candidateUi)
            }
        }
    }

    private fun selectCandidate(candidateUi: CandidateUi) {
        _state.update {
            it.copy(selectedCandidate = candidateUi)
        }
    }
}