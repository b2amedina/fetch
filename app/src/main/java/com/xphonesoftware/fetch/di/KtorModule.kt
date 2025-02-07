package com.xphonesoftware.fetch.di

import com.xphonesoftware.fetch.data.CandidateRepository
import com.xphonesoftware.fetch.domain.CandidateDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@InstallIn(SingletonComponent::class)
@Module
object KtorModule {
    @Provides
    fun provideKtorHttpClient(): HttpClient {
        return HttpClient(CIO.create()) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }

    @Provides
    fun provideCandidateDataSource(httpClient: HttpClient): CandidateDataSource {
        return CandidateRepository(httpClient)
    }
}