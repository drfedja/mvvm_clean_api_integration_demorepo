package com.nfinnova.data.models

import com.squareup.moshi.Json

data class RepoDetailsResponse(
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    val owner: OwnerDto,
    @Json(name = "forks_count")
    val forksCount: Int,
    @Json(name = "watchers_count")
    val watchersCount: Int,
    @Json(name = "open_issues_count")
    val openIssuesCount: Int,
)

data class OwnerDto(
    val login: String,
    val id: Int,
    @Json(name = "gravatar_id")
    val gravatarId: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)
