package com.xphonesoftware.fetch.data

import kotlinx.serialization.Serializable

@Serializable
data class CandidateDto(
    val id: Int,
    val listId: Int,
    val name: String?,
)
