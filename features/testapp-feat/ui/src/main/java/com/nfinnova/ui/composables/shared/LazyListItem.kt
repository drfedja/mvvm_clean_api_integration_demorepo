package com.nfinnova.ui.composables.shared

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nfinnova.ui.composables.roundedCornerShape

@Composable
internal fun LazyListItem(
    label1: String,
    value1: String,
    label2: String,
    value2: String,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
            .combinedClickable(
                interactionSource = interactionSource,
                indication = ripple(),
                onClick = {
                    onClick()
                }
            ),
        shape = roundedCornerShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            Column {
                Row {
                    Text(
                        modifier = Modifier.padding(16.dp).weight(1.0f),
                        text = label1
                    )
                    Text(
                        modifier = Modifier.padding(16.dp).weight(1.0f),
                        text = value1
                    )
                }
                Row {
                    Text(
                        modifier = Modifier.padding(16.dp).weight(1.0f),
                        text = label2
                    )
                    Text(
                        modifier = Modifier.padding(16.dp).weight(1.0f),
                        text = value2
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun ListItemPreview() {
    LazyListItem("repoName", "test repo", "issues","10")
}
