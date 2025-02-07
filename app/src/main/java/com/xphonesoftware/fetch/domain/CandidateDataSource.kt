package com.xphonesoftware.fetch.domain;

interface CandidateDataSource {
    suspend fun getCandidates(): List<Candidate>
}
