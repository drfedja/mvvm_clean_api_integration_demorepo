package com.nfinnova.data.di

import com.nfinnova.data.repository.GitHubRepositoryImpl
import com.nfinnova.data.usecases.GetRepoDetailsUseCaseImpl
import com.nfinnova.data.usecases.GetUserRepositoriesUseCaseImpl
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetRepoDetailsUseCase
import com.nfinnova.domain.usecases.GetUserRepositoriesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface GitHubModule {

    @Binds
    @ActivityRetainedScoped
    fun bindGitHubRepository(
        gitHubRepositoryImpl: GitHubRepositoryImpl
    ): GitHubRepository

    @Binds
    @ActivityRetainedScoped
    fun bindGetUserRepositoriesUseCase(
        getUserRepositoriesUseCaseImpl: GetUserRepositoriesUseCaseImpl
    ): GetUserRepositoriesUseCase

    @Binds
    @ActivityRetainedScoped
    fun bindGetRepoDetailsUseCase(
        getRepoDetailsUseCaseImpl: GetRepoDetailsUseCaseImpl
    ): GetRepoDetailsUseCase
}