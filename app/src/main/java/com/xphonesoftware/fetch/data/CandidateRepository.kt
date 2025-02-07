package com.xphonesoftware.fetch.data

import com.xphonesoftware.fetch.BuildConfig
import com.xphonesoftware.fetch.domain.Candidate
import com.xphonesoftware.fetch.domain.CandidateDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import jakarta.inject.Inject

class CandidateRepository @Inject constructor(
    private val httpClient: HttpClient
) : CandidateDataSource {
    override suspend fun getCandidates(): List<Candidate> {
        val response = httpClient.get(
            urlString = BuildConfig.BASE_URL.plus("hiring.json")
        )
        if (response.status.isSuccess()) {
            val candidates = response.body<List<CandidateDto>>()
                .filter { !it.name.isNullOrBlank() }
                .sortedWith( compareBy({ it.listId }, { it.id }) )
            return candidates.map { it.toCandidate() }
        }
        return emptyList()
    }
}