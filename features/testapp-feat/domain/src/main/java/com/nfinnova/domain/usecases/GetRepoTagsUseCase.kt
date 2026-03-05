package com.nfinnova.domain.usecases

import com.nfinnova.domain.models.TagData

interface GetRepoTagsUseCase {
    suspend fun invoke(userName: String, repoName: String): List<TagData>
}
