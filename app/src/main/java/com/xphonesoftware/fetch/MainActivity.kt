package com.xphonesoftware.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.xphonesoftware.fetch.data.CandidateRepository
import com.xphonesoftware.fetch.presentation.CandidateListScreen
import com.xphonesoftware.fetch.presentation.CandidateListState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var candidateDataSource: CandidateRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            candidateDataSource.getCandidates()
        }

        enableEdgeToEdge()
        setContent {
            CandidateListScreen(
                state = CandidateListState(
                    isLoading = true,
                    candidates = emptyList(),
                    selectedCandidate = null),
                onAction = { TODO() },
            )
        }
    }
}