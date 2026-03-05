package com.nfinnova.ui.composables

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nfinnova.core_ui.composable.Screen
import com.nfinnova.ui.viewmodels.UserReposViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nfinnova.core_ui.screen_state.ScreenState
import com.nfinnova.domain.models.UserRepository

internal val roundedCornerShape = RoundedCornerShape(8.dp)

@Composable
fun UserReposScreen(
    onNavigate: () -> Unit
) {
    Screen(
        viewModel = hiltViewModel<UserReposViewModel>()
    ) { viewState ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            UserReposContent(
                repoList = viewState.userRepoList,
                onNavigate = onNavigate
            )

            if (viewState.screenState is ScreenState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (viewState.screenState is ScreenState.Failure) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Something went wrong"
                )
            }
        }
    }

}

@Composable
fun UserReposContent(
    repoList: List<UserRepository>,
    onNavigate: () -> Unit
) {
    Column (modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 30.dp, bottom = 16.dp)) {
        LazyColumn{
            items(repoList) { listItem ->
                ListItem(
                    repoName = listItem.repositoryName,
                    issuesOpened = listItem.openedIssues,
                    onClick = onNavigate
                )
            }
        }
    }
}

@Composable
fun ListItem(
    repoName: String,
    issuesOpened: Int,
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
                        text = "Repo name: "
                    )
                    Text(
                        modifier = Modifier.padding(16.dp).weight(1.0f),
                        text = repoName
                    )
                }
                Row {
                    Text(
                        modifier = Modifier.padding(16.dp).weight(1.0f),
                        text = "no. issues opened: "
                    )
                    Text(
                        modifier = Modifier.padding(16.dp).weight(1.0f),
                        text = issuesOpened.toString()
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ListItemPreview() {
    ListItem("repoName", 10)
}
