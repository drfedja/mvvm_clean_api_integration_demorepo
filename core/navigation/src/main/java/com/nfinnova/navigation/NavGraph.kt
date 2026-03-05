package com.nfinnova.navigation

interface NavGraph {
    val route: String

    data object RepoHome: NavGraph {
        override val route = "/"
    }
    data object RepoDetails: NavGraph {
        override val route = "/repoDetails"
    }
}
