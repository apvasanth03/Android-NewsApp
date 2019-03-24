package com.vasanth.data.repository

import com.vasanth.data.model.NewsArticleEntity
import io.reactivex.Single

/**
 * The interface provides abstraction over remote layer.
 *
 * @author Vasanth
 */
interface NewsArticleRemoteDataStore {

    fun getNewsArticles(): Single<List<NewsArticleEntity>>

}