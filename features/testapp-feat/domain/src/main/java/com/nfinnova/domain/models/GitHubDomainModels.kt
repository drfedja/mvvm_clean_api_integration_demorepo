package com.nfinnova.domain.models

data class UserRepository(
    val repositoryName: String,
    val openedIssues: Int
)

data class RepoDetailsHeaderData(
    val userAvatarUrl: String,
    val userName: String,
    val repoName: String,
    val numberOfForks: Int,
    val numberOfWatchers: Int
) {
    companion object {
        val EMPTY = RepoDetailsHeaderData(
            userAvatarUrl = "",
            userName = "",
            repoName = "",
            numberOfForks = 0,
            numberOfWatchers = 0
        )
    }
}

data class TagData(
    val commitName: String,
    val commitSha: String
)
