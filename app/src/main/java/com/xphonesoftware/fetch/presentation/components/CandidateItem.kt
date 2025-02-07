package com.xphonesoftware.fetch.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xphonesoftware.fetch.domain.Candidate
import com.xphonesoftware.fetch.presentation.models.CandidateUi
import com.xphonesoftware.fetch.presentation.models.toCandidateUi
import com.xphonesoftware.fetch.ui.theme.FetchTheme

@Composable
fun CandidateItem(
    candidateUi: CandidateUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val selectedColor = if (isSelected) CardDefaults.cardColors()
        .copy(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    else CardDefaults.cardColors()
    Card(
        colors = selectedColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
            )
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = candidateUi.iconRes),
                contentDescription = candidateUi.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = candidateUi.name,
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
private fun CandidateItemPreview() {
    FetchTheme {
        CandidateItem(
            candidateUi = Candidate(1, 1, "Candidate 1").toCandidateUi(),
            isSelected = false,
            onClick = {}
        )
    }
}
