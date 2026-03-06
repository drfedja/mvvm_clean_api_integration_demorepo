package com.nfinnova.data.usecases

import com.nfinnova.domain.models.RepoDetailsData
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetRepoDetailsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class GetRepoDetailsUseCaseImpl @Inject constructor(
    private val repository: GitHubRepository
) : GetRepoDetailsUseCase {

    override suspend fun invoke(user: String, repo: String): Result<RepoDetailsData> =
        coroutineScope {
            try {
                val headerDeferred = async {
                    repository.getRepoHeaderData(user, repo)
                }

                val tagsDeferred = async {
                    repository.getRepoTags(user, repo)
                }

                val headerResult = headerDeferred.await()
                val tagsResult = tagsDeferred.await()

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
                    Result.failure(
                        headerResult.exceptionOrNull() ?: Exception("Unknown error")
                    )
                }
            } catch (e: Throwable) {
                Result.failure(e)
            }
        }
}
