package com.vasanth.newsapp.di.module.app

import com.vasanth.data.repository.NewsArticleRemoteDataStore
import com.vasanth.remote.NewsArticleRemoteDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteModule {

    @Binds
    abstract fun bindRemoteDataStore(newsArticleRemoteDataStoreImpl: NewsArticleRemoteDataStoreImpl): NewsArticleRemoteDataStore

}