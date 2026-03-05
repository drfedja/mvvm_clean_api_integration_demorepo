package com.nfinnova.data.models

internal data class TagDto(
    val name: String,
    val commit: CommitDto
)

internal data class CommitDto(
    val sha: String,
    val url: String
)
