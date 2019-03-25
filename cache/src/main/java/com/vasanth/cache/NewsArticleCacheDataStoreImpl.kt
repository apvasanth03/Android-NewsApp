package com.vasanth.cache

import com.vasanth.cache.db.NewsArticlesDatabase
import com.vasanth.cache.mapper.CachedNewsArticleMapper
import com.vasanth.data.model.NewsArticleEntity
import com.vasanth.data.repository.NewsArticleCacheDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A Class provides implementation for NewsArticleCacheDataStore.
 *
 * @author Vasanth
 */
@Singleton
class NewsArticleCacheDataStoreImpl @Inject constructor(
    private val newsArticlesDatabase: NewsArticlesDatabase,
    private val mapper: CachedNewsArticleMapper
) : NewsArticleCacheDataStore {

    // NewsArticleCache Methods.
    override fun clearNewsArticles(): Completable {
        return newsArticlesDatabase.cachedNewsArticlesDao().deleteNewsArticles()
    }

    override fun saveNewsArticles(newsArticles: List<NewsArticleEntity>): Completable {
        return newsArticlesDatabase.cachedNewsArticlesDao().insertNewsArticles(
            newsArticles.map { mapper.mapToCached(it) }
        )
    }

    override fun getNewsArticles(): Observable<List<NewsArticleEntity>> {
        return newsArticlesDatabase.cachedNewsArticlesDao().getNewsArticles()
            .map { it.map { mapper.mapFromCached(it) } }
    }

    override fun areNewsArticlesCached(): Single<Boolean> {
        return newsArticlesDatabase.cachedNewsArticlesDao().getNewsArticlesCount().map { it > 0 }
    }
}