package com.nfinnova.data.mappers

import com.nfinnova.data.models.RepoDetailsResponse
import com.nfinnova.data.models.RepoDto
import com.nfinnova.data.models.TagDto
import com.nfinnova.domain.models.RepoDetailsHeaderData
import com.nfinnova.domain.models.TagData
import com.nfinnova.domain.models.UserRepository

internal fun RepoDto.toDomain() = UserRepository(
    repositoryName = name,
    openedIssues = openIssues
)

internal fun RepoDetailsResponse.toDomain() = RepoDetailsHeaderData(
    userAvatarUrl = owner.avatarUrl,
    userName = owner.login,
    repoName = name,
    numberOfForks = forksCount,
    numberOfWatchers = watchersCount
)

internal fun TagDto.toDomain() = TagData(
    commitName = name,
    commitSha = commit.sha
)
