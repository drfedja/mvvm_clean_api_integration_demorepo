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
)

data class TagData(
    val commitName: String,
    val commitSha: String
)
