package com.xphonesoftware.fetch.presentation

import com.xphonesoftware.fetch.presentation.models.CandidateUi

sealed interface CandidateListAction {
    data class OnItemClicked(val candidateUi: CandidateUi) : CandidateListAction
}