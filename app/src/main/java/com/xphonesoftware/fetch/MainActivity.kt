package com.xphonesoftware.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.xphonesoftware.fetch.presentation.CandidateListScreen
import com.xphonesoftware.fetch.presentation.CandidateListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CandidateListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CandidateListScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onAction = { action -> viewModel.onAction(action) },
            )
        }
    }
}