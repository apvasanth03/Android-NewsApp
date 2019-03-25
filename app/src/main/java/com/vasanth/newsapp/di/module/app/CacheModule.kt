package com.vasanth.newsapp.di.module.app

import android.app.Application
import com.vasanth.cache.NewsArticleCacheDataStoreImpl
import com.vasanth.cache.db.NewsArticlesDatabase
import com.vasanth.data.repository.NewsArticleCacheDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesDataBase(application: Application): NewsArticlesDatabase {
            return NewsArticlesDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindCacheDataStore(cacheDataStoreImpl: NewsArticleCacheDataStoreImpl): NewsArticleCacheDataStore

}