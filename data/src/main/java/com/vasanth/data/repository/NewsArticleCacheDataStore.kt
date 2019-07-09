package com.vasanth.data.repository

import com.vasanth.data.model.NewsArticleEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * The interface provides abstraction over cache layer.
 *
 * @author Vasanth
 */
interface NewsArticleCacheDataStore {

    fun clearNewsArticles(): Completable

    fun saveNewsArticles(newsArticles: List<NewsArticleEntity>): Completable

    fun getNewsArticles(): Single<List<NewsArticleEntity>>

    fun areNewsArticlesCached(): Single<Boolean>

}