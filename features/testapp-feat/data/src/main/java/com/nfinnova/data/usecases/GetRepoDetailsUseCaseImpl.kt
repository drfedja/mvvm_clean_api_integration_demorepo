package com.nfinnova.data.usecases

import com.nfinnova.domain.models.RepoDetailsData
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetRepoDetailsUseCase
import javax.inject.Inject

internal class GetRepoDetailsUseCaseImpl @Inject constructor(
    private val repository: GitHubRepository
) : GetRepoDetailsUseCase {

    override suspend fun invoke(user: String, repo: String): Result<RepoDetailsData> {
        return try {
            val headerResult = repository.getRepoHeaderData(user, repo)
            val tagsResult = repository.getRepoTags(user, repo)

            if (headerResult.isSuccess) {
                val header = headerResult.getOrThrow()
                val tags = tagsResult.getOrElse { emptyList() }

                Result.success(
                    RepoDetailsData(
                        repoDetailsHeaderData = header,
                        repoTags = tags
                    )
                )
            } else {
                Result.failure(headerResult.exceptionOrNull() ?: Exception("Unknown error"))
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}
