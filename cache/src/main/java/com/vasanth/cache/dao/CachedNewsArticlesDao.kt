package com.vasanth.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vasanth.cache.db.NewsArticleDBConstants
import com.vasanth.cache.model.CachedNewsArticle
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CachedNewsArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsArticles(newsArticles: List<CachedNewsArticle>): Completable

    @Query(NewsArticleDBConstants.QUERY_NEWS_ARTICLES)
    fun getNewsArticles(): Observable<List<CachedNewsArticle>>

    @Query(NewsArticleDBConstants.QUERY_NEWS_ARTICLES_COUNT)
    fun getNewsArticlesCount(): Single<Int>


    @Query(NewsArticleDBConstants.DELETE_NEWS_ARTICLES)
    fun deleteNewsArticles(): Completable

}