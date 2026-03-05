package com.nfinnova.data.di

import com.nfinnova.data.api.GitHubApi
import com.nfinnova.provider.BaseUrlProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class GitHubApiModule {

    @Provides
    @Singleton
    @Named(GIT_HUB_CLIENT)
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideGitHubApi(
        baseUrlProvider: BaseUrlProvider,
        @Named(GIT_HUB_CLIENT) client: OkHttpClient
    ): GitHubApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit
            .Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrlProvider.getGithubApiBaseUrl())
            .client(client)
            .build().create(GitHubApi::class.java)
    }

    companion object {
        private const val GIT_HUB_CLIENT = "github_client"
    }
}
