package com.vasanth.domain.repository

import com.vasanth.domain.model.NewsArticle
import io.reactivex.Observable

/**
 * The interface provides abstraction over data layer.
 *
 * @author Vasanth
 */
interface NewsArticlesRepository {

    fun getNewsArticles(): Observable<List<NewsArticle>>

}