package com.nfinnova.navigation

interface NavGraph {
    val route: String

    data object RepoHome: NavGraph {
        override val route = "/"
    }

    data object RepoDetails : NavGraph {
        const val USER = "user"
        const val REPO = "repo"

        override val route = "/repoDetails/{$USER}/{$REPO}"

        fun createRoute(user: String, repo: String) =
            "/repoDetails/$user/$repo"
    }
}
