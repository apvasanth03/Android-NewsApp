package com.vasanth.domain.repository

import com.vasanth.domain.model.NewsArticle
import io.reactivex.Single

/**
 * The interface provides abstraction over data layer.
 *
 * @author Vasanth
 */
interface NewsArticlesRepository {

    fun getNewsArticles(): Single<List<NewsArticle>>

}