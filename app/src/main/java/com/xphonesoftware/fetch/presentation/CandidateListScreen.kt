@file:OptIn(ExperimentalMaterial3Api::class)

package com.xphonesoftware.fetch.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xphonesoftware.fetch.domain.Candidate
import com.xphonesoftware.fetch.presentation.components.AppBar
import com.xphonesoftware.fetch.presentation.components.CandidateItem
import com.xphonesoftware.fetch.presentation.models.CandidateUi
import com.xphonesoftware.fetch.presentation.models.toCandidateUi
import com.xphonesoftware.fetch.ui.theme.FetchTheme

@Composable
fun CandidateListScreen(
    state: CandidateListState,
    onAction: (CandidateListAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val groupedItems: Map<String, List<CandidateUi>> =
        state.candidates.groupBy { it.listId.toString() }

    FetchTheme {
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                AppBar(scrollBehavior = scrollBehavior)
            },
        ) { innerPadding ->
            if (state.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {
                    groupedItems.forEach { (groupKey, itemsInCategory) ->
                        item {
                            Text(
                                text = "Group $groupKey",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                        items(itemsInCategory) { candidateUi ->
                            CandidateItem(
                                candidateUi = candidateUi,
                                onClick = {
                                    onAction(CandidateListAction.OnItemClicked(candidateUi))
                                },
                                modifier = Modifier.fillMaxWidth(),
                                isSelected = candidateUi == state.selectedCandidate
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CandidateListScreenPreview() {
    CandidateListScreen(
        state = CandidateListState(
            isLoading = false,
            candidates = (1..100).map {
                Candidate(it, it, "Candidate $it").toCandidateUi()
            },
            selectedCandidate = null
        ),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        onAction = {}
    )
}