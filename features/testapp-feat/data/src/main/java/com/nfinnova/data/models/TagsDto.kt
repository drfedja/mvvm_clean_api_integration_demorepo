package com.nfinnova.data.models

data class TagDto(
    val name: String,
    val commit: CommitDto
)

data class CommitDto(
    val sha: String,
    val url: String
)
