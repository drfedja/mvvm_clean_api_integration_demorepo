package com.nfinnova.domain.repository

import com.nfinnova.domain.models.RepoDetailsHeaderData
import com.nfinnova.domain.models.TagData
import com.nfinnova.domain.models.UserRepository

interface GitHubRepository {
    suspend fun getUserRepositories(username: String): Result<List<UserRepository>>
    suspend fun getRepoHeaderData(username: String, repoName: String): Result<RepoDetailsHeaderData>
    suspend fun getRepoTags(username: String, repoName: String): List<TagData>
}
