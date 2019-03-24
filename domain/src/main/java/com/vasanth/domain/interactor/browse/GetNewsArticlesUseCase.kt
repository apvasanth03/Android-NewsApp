package com.vasanth.domain.interactor.browse

import com.vasanth.domain.executor.PostExecutionThread
import com.vasanth.domain.interactor.ObservableUseCase
import com.vasanth.domain.model.NewsArticle
import com.vasanth.domain.repository.NewsArticlesRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * The useCase responsible for getting NewsArticles.
 *
 * @author Vasanth
 */
class GetNewsArticlesUseCase @Inject constructor(
    private val newsArticlesRepository: NewsArticlesRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<NewsArticle>, Nothing?>(postExecutionThread) {

    // ObservableUseCase Methods.
    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<NewsArticle>> {
        return newsArticlesRepository.getNewsArticles()
    }
}