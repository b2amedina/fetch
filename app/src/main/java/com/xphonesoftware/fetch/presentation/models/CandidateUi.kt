package com.xphonesoftware.fetch.presentation.models

import androidx.annotation.DrawableRes
import com.xphonesoftware.fetch.domain.Candidate
import com.xphonesoftware.fetch.presentation.getDrawableIdForCandidate

data class CandidateUi (
    val id: Int,
    val listId: Int,
    val name: String,
    @DrawableRes val iconRes: Int,
)

fun Candidate.toCandidateUi(): CandidateUi {
    return CandidateUi(
        id = id,
        listId = listId,
        name = name!!,
        iconRes = getDrawableIdForCandidate(name)
    )
}