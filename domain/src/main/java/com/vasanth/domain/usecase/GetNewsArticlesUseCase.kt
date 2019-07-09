package com.vasanth.domain.usecase

import com.vasanth.domain.model.NewsArticle
import com.vasanth.domain.repository.NewsArticlesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * The useCase responsible for getting NewsArticles.
 *
 * @author Vasanth
 */
open class GetNewsArticlesUseCase @Inject constructor(private val newsArticlesRepository: NewsArticlesRepository) {

    open fun execute(): Single<List<NewsArticle>> {
        return newsArticlesRepository.getNewsArticles()
    }


}
