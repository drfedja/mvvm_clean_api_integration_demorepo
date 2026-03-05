package com.nfinnova.data.di

import com.nfinnova.data.repository.GitHubRepositoryImpl
import com.nfinnova.data.usecases.GetHeaderRepoDataUseCaseImpl
import com.nfinnova.data.usecases.GetRepoTagsUseCaseImpl
import com.nfinnova.data.usecases.GetUserRepositoriesUseCaseImpl
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetHeaderRepoDataUseCase
import com.nfinnova.domain.usecases.GetRepoTagsUseCase
import com.nfinnova.domain.usecases.GetUserRepositoriesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface GitHubModule {

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
    fun bindGetHeaderRepoDataUseCase(
        getHeaderRepoDataUseCaseImpl: GetHeaderRepoDataUseCaseImpl
    ): GetHeaderRepoDataUseCase

    @Binds
    @ActivityRetainedScoped
    fun bindGetRepoTagsUseCase(
        getRepoTagsUseCaseImpl: GetRepoTagsUseCaseImpl
    ): GetRepoTagsUseCase
}