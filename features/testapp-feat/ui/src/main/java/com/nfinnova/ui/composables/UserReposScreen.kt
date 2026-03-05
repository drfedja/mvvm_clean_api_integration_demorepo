package com.nfinnova.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nfinnova.core_ui.composable.Screen
import com.nfinnova.ui.viewmodels.UserReposViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun UserReposScreen(
    onNavigate: () -> Unit
) {
    Screen(
        viewModel = hiltViewModel<UserReposViewModel>()
    ) { viewState ->
        UserReposContent(
            someData = "go to next page",
            someList = viewState.userRepoList,
            onNavigate = onNavigate
        )
    }

}

@Composable
fun UserReposContent(
    someData: String,
    someList: List<String>,
    onNavigate: () -> Unit
) {
    Column {
        ElevatedButton(
            modifier = Modifier.padding(10.dp),
            onClick = onNavigate
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = someData
            )
        }
        LazyColumn{
            items(someList) { listItem ->
                ListItem(listItem)
            }
        }
    }
}

@Composable
fun ListItem(listItem: String) {
    Box {
        Text(
            modifier = Modifier.padding(16.dp),
            text = listItem
        )
    }
}
