package com.nfinnova.domain.usecases

import com.nfinnova.domain.models.UserRepository

interface GetUserRepositoriesUseCase {
    suspend fun invoke(userName: String): Result<List<UserRepository>>
}