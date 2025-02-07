package com.xphonesoftware.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.xphonesoftware.fetch.presentation.CandidateListScreen
import com.xphonesoftware.fetch.presentation.CandidateListState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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