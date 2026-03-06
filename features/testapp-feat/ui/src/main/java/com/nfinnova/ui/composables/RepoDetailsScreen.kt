package com.nfinnova.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import com.nfinnova.core_ui.composable.Screen
import com.nfinnova.core_ui.screen_state.ScreenState
import com.nfinnova.domain.models.RepoDetailsHeaderData
import com.nfinnova.domain.models.TagData
import com.nfinnova.ui.R
import com.nfinnova.ui.composables.shared.LazyListItem
import com.nfinnova.ui.viewmodels.RepoDetailsViewModel

@Composable
fun RepoDetailsScreen(
    user: String,
    repo: String
) {
    Screen(
        viewModel = hiltViewModel<RepoDetailsViewModel>()
    ) { viewState ->

        LaunchedEffect(Unit) {
            viewState.loadUserRepoData(user, repo)
        }

        Box(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp)
        ) {
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
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 16.dp)
            ) {
                RepoHeader(
                    data = viewState.repoDetailsHeaderData,
                )
                RepoTagsList(viewState.repoTags)
            }
        }
    }
}

@Composable
private fun RepoTagsList(
    repoList: List<TagData>,
) {
    Column (modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 30.dp, bottom = 16.dp)) {
        LazyColumn{
            items(repoList) { listItem ->
                LazyListItem(
                    label1 = stringResource(R.string.commit_name),
                    value1 = listItem.commitName,
                    label2 = stringResource(R.string.commit_sha),
                    value2 = listItem.commitSha,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun RepoHeader(
    data: RepoDetailsHeaderData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            model = data.userAvatarUrl,
            contentDescription = "${data.userName} avatar",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_no_image),
                    contentDescription = "Error loading image",
                    modifier = Modifier.size(24.dp)
                )
            }
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = data.userName, style = MaterialTheme.typography.titleMedium)
            Text(text = data.repoName, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.forks_count, data.numberOfForks),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(id = R.string.watchers_count, data.numberOfWatchers),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
