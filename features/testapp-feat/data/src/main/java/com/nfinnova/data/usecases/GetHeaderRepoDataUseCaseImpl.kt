package com.nfinnova.data.usecases

import com.nfinnova.domain.models.RepoDetailsHeaderData
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetHeaderRepoDataUseCase
import javax.inject.Inject

class GetHeaderRepoDataUseCaseImpl @Inject constructor(
    private val repository: GitHubRepository
) : GetHeaderRepoDataUseCase {

    override suspend fun invoke(userName: String, repoName: String): Result<RepoDetailsHeaderData> {
        return repository.getRepoHeaderData(username = userName, repoName = repoName)
    }
}
