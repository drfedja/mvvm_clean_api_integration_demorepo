package com.nfinnova.domain.usecases

import com.nfinnova.domain.models.RepoDetailsHeaderData

interface GetHeaderRepoDataUseCase {
    suspend fun invoke(userName: String, repoName: String): Result<RepoDetailsHeaderData>
}
