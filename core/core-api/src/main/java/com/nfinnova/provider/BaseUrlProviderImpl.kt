package com.nfinnova.provider

import com.nfinnova.api.BuildConfig
import javax.inject.Inject

class BaseUrlProviderImpl @Inject constructor() : BaseUrlProvider {
    override fun getGithubApiBaseUrl(): String = BuildConfig.GITHUB_BASE_URL
}
