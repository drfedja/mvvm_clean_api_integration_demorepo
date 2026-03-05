package com.nfinnova.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nfinnova.core_ui.composable.Screen
import com.nfinnova.ui.viewmodels.UserReposViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nfinnova.core_ui.screen_state.ScreenState
import com.nfinnova.domain.models.UserRepository
import com.nfinnova.ui.R
import com.nfinnova.ui.composables.shared.LazyListItem

internal val roundedCornerShape = RoundedCornerShape(8.dp)

@Composable
fun UserReposScreen(
    onNavigate: (userName: String, repoName: String) -> Unit
) {
    Screen(
        viewModel = hiltViewModel<UserReposViewModel>()
    ) { viewState ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            UserReposContent(
                repoList = viewState.userRepoList,
                userName = viewState.repoUserName,
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
private fun UserReposContent(
    repoList: List<UserRepository>,
    userName: String,
    onNavigate: (username: String, repoName: String) -> Unit
) {
    Column (modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 30.dp, bottom = 16.dp)) {
        LazyColumn{
            items(repoList) { listItem ->
                LazyListItem(
                    label1 = stringResource(R.string.repo_name_label),
                    value1 = listItem.repositoryName,
                    label2 = stringResource(R.string.issues_opened_label),
                    value2 = listItem.openedIssues.toString(),
                    onClick = {
                        onNavigate(
                            userName,
                            listItem.repositoryName
                        )
                    }
                )
            }
        }
    }
}

