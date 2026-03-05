package com.nfinnova.data.repository

import com.nfinnova.api.ApiHandler
import com.nfinnova.data.api.GitHubApi
import com.nfinnova.data.mappers.toDomain
import com.nfinnova.domain.models.RepoDetailsHeaderData
import com.nfinnova.domain.models.TagData
import com.nfinnova.domain.models.UserRepository
import com.nfinnova.domain.repository.GitHubRepository
import javax.inject.Inject

internal class GitHubRepositoryImpl @Inject constructor(
    private val gitHubApi: GitHubApi
) : GitHubRepository, ApiHandler {

    override suspend fun getUserRepositories(username: String): Result<List<UserRepository>> {
        return handleApi(
            execute = { gitHubApi.getUserRepos(username) },
            mapper = { map { it.toDomain() } }
        )
    }

    override suspend fun getRepoHeaderData(
        username: String,
        repoName: String
    ): Result<RepoDetailsHeaderData> {
        return handleApi(
            execute = { gitHubApi.getRepoDetails(username, repoName) },
            mapper = {
                this.toDomain()
            }
        )
    }

    override suspend fun getRepoTags(
        username: String,
        repoName: String
    ): Result<List<TagData>> {
        return handleApi(
            execute = { gitHubApi.getRepoTags(username, repoName) },
            mapper = { map { it.toDomain() } }
        )
    }
}
