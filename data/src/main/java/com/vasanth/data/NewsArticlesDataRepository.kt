package com.vasanth.data

import com.vasanth.data.mapper.NewsArticleEntityMapper
import com.vasanth.data.model.NewsArticleEntity
import com.vasanth.data.repository.NewsArticleCacheDataStore
import com.vasanth.data.repository.NewsArticleRemoteDataStore
import com.vasanth.domain.model.NewsArticle
import com.vasanth.domain.repository.NewsArticlesRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * A Class provides implementation for NewsArticlesRepository.
 *
 * @author Vasanth
 */
class NewsArticlesDataRepository @Inject constructor(
    private val remoteDataStore: NewsArticleRemoteDataStore,
    private val cacheDataStore: NewsArticleCacheDataStore,
    private val mapper: NewsArticleEntityMapper
) : NewsArticlesRepository {

    // NewsArticlesRepository Methods.
    override fun getNewsArticles(): Observable<List<NewsArticle>> {
        return cacheDataStore.areNewsArticlesCached().toObservable()
            .flatMap { areCached ->
                if (areCached) {
                    updateCacheWithLatestDataFromRemote()
                    return@flatMap cacheDataStore.getNewsArticles()
                } else {
                    return@flatMap getNewsArticlesFromRemoteAndSaveItToCache().toObservable()
                }
            }
            .map { newsArticles ->
                newsArticles.map { mapper.mapFromEntity(it) }
            }
    }

    // Private Methods.
    /**
     * A method gets NewsArticles from remote dataStore & saves it to cache dataStore & returns the newsArticles.
     */
    private fun getNewsArticlesFromRemoteAndSaveItToCache(): Single<List<NewsArticleEntity>> {
        return remoteDataStore.getNewsArticles()
            .flatMap { newsArticles ->
                cacheDataStore.saveNewsArticles(newsArticles)
                    .toSingleDefault(newsArticles)
            }
    }

    /**
     * A method updates cache dataStore with latest data from remote dataStore in background.
     */
    private fun updateCacheWithLatestDataFromRemote() {
        getNewsArticlesFromRemoteAndSaveItToCache()
            .subscribeOn(Schedulers.io())
            .onErrorReturnItem(ArrayList())
            .subscribe()

    }
}