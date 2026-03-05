package com.nfinnova.domain.usecases

import com.nfinnova.domain.models.RepoDetailsData

interface GetRepoDetailsUseCase {
    suspend fun invoke(user: String, repo: String): Result<RepoDetailsData>
}
