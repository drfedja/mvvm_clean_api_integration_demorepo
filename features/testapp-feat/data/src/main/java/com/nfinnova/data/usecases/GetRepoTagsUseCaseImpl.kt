package com.nfinnova.data.usecases

import com.nfinnova.domain.models.TagData
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetRepoTagsUseCase
import javax.inject.Inject

class GetRepoTagsUseCaseImpl @Inject constructor(
    private val repository: GitHubRepository
) : GetRepoTagsUseCase {

    override suspend fun invoke(userName: String, repoName: String): List<TagData> {
        return repository.getRepoTags(userName, repoName)
    }
}
