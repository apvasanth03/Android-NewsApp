package com.vasanth.domain.interactor.browse

import com.nhaarman.mockitokotlin2.verify
import com.vasanth.domain.executor.PostExecutionThread
import com.vasanth.domain.repository.NewsArticlesRepository
import com.vasanth.domain.test.factory.NewsArticleFactory
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNewsArticlesUseCaseTest {

    @Mock
    lateinit var newsArticlesRepository: NewsArticlesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @InjectMocks
    lateinit var getNewsArticlesUseCase: GetNewsArticlesUseCase

    @Test
    fun getNewsArticles_shouldCallRepository() {
        val newsArticle = NewsArticleFactory.makeNewsArticle()
        `when`(newsArticlesRepository.getNewsArticles()).thenReturn(Observable.just(listOf(newsArticle)))

        val testObserver = getNewsArticlesUseCase.buildUseCaseObservable().test()

        verify(newsArticlesRepository).getNewsArticles()
    }

    @Test
    fun getNewsArticles_shouldReturnDataFromRepository() {
        val newsArticle = NewsArticleFactory.makeNewsArticle()
        `when`(newsArticlesRepository.getNewsArticles()).thenReturn(Observable.just(listOf(newsArticle)))

        val testObserver = getNewsArticlesUseCase.buildUseCaseObservable().test()

        testObserver.assertValue(listOf(newsArticle))
    }

}