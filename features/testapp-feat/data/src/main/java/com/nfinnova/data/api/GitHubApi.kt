package com.nfinnova.data.api

import com.nfinnova.data.models.RepoDetailsResponse
import com.nfinnova.data.models.RepoDto
import com.nfinnova.data.models.TagDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GitHubApi {
    @GET("users/{user}/repos")
    suspend fun getUserRepos(
        @Path("user") user: String
    ): Response<List<RepoDto>>

    @GET("repos/{user}/{repo}")
    suspend fun getRepoDetails(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): Response<RepoDetailsResponse>

    @GET("repos/{user}/{repo}/tags")
    suspend fun getRepoTags(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): Response<List<TagDto>>
}
