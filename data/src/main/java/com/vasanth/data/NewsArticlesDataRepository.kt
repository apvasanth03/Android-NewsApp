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
import javax.inject.Singleton

/**
 * A Class provides implementation for NewsArticlesRepository.
 *
 * @author Vasanth
 */
@Singleton
class NewsArticlesDataRepository @Inject constructor(
    private val remoteDataStore: NewsArticleRemoteDataStore,
    private val cacheDataStore: NewsArticleCacheDataStore,
    private val mapper: NewsArticleEntityMapper
) : NewsArticlesRepository {

    // NewsArticlesRepository Methods.
    override fun getNewsArticles(): Single<List<NewsArticle>> {
        return getNewsArticlesFromRemoteAndSaveItToCache()
            .onErrorResumeNext { error ->
                return@onErrorResumeNext cacheDataStore.areNewsArticlesCached()
                    .flatMap { areCached ->
                        if (areCached) {
                            return@flatMap cacheDataStore.getNewsArticles()
                        } else {
                            return@flatMap Single.error<List<NewsArticleEntity>>(error)
                        }
                    }
            }.map { newsArticles ->
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
                cacheDataStore.clearNewsArticles()
                    .andThen(cacheDataStore.saveNewsArticles(newsArticles))
                    .toSingleDefault(newsArticles)
            }
    }
}