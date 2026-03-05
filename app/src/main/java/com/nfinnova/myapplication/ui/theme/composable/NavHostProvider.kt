package com.nfinnova.myapplication.ui.theme.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nfinnova.core_ui.composable.LocalNavController
import com.nfinnova.navigation.NavGraph
import com.nfinnova.ui.composables.RepoDetailsScreen
import com.nfinnova.ui.composables.UserReposScreen

@Composable
fun NavHostProvider() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            navController = navController,
            startDestination = NavGraph.RepoHome.route
        ) {
            composable(
                route = NavGraph.RepoHome.route
            ) {
                UserReposScreen(
                    onNavigate = { userName, repoName ->
                        navController.navigate(
                            NavGraph.RepoDetails.createRoute(userName, repoName)
                        )
                    }
                )
            }

            composable(
                route = NavGraph.RepoDetails.route
            ) { backstackEntry ->
                val user = backstackEntry
                    .arguments?.getString(NavGraph.RepoDetails.USER).orEmpty()
                val repo = backstackEntry
                    .arguments?.getString(NavGraph.RepoDetails.REPO).orEmpty()

                RepoDetailsScreen(
                    user = user,
                    repo = repo
                )
            }
        }
    }
}
