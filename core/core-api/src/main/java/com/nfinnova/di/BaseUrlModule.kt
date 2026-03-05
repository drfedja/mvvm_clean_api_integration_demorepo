package com.nfinnova.di

import com.nfinnova.provider.BaseUrlProvider
import com.nfinnova.provider.BaseUrlProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseUrlModule {

    @Binds
    @Singleton
    abstract fun bindBaseUrlProvider(
        baseUrlProviderImpl: BaseUrlProviderImpl
    ): BaseUrlProvider
}