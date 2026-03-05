package com.nfinnova.domain.models

data class RepoDetailsData(
    val repoDetailsHeaderData: RepoDetailsHeaderData,
    val repoTags: List<TagData>
)
