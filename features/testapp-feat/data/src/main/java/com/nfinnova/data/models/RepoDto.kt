package com.nfinnova.data.models

import com.squareup.moshi.Json

internal data class RepoDto(
    val name: String,
    val id: Int,
    @Json(name = "open_issues")
    val openIssues: Int,
    val owner: OwnerDto
)
