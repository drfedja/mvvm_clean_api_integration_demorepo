package com.nfinnova.data.usecases

import com.nfinnova.domain.models.UserRepository
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetUserRepositoriesUseCase
import javax.inject.Inject

internal class GetUserRepositoriesUseCaseImpl @Inject constructor(
    private val repository: GitHubRepository
) : GetUserRepositoriesUseCase {

    override suspend fun invoke(userName: String): Result<List<UserRepository>> {
        return repository.getUserRepositories(username = userName)
    }
}
