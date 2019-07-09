package com.vasanth.domain.usecase

import com.nhaarman.mockitokotlin2.times
import com.vasanth.domain.repository.NewsArticlesRepository
import com.vasanth.domain.test.factory.NewsArticleFactory
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNewsArticlesUseCaseTest {

    @Mock
    lateinit var newsArticlesRepository: NewsArticlesRepository

    @InjectMocks
    lateinit var getNewsArticlesUseCase: GetNewsArticlesUseCase

    @Test
    fun getNewsArticles_ShouldCallRepository() {
        val newsArticle = NewsArticleFactory.makeNewsArticle()
        `when`(newsArticlesRepository.getNewsArticles()).thenReturn(Single.just(listOf(newsArticle)))

        val testObserver = getNewsArticlesUseCase.execute().test()

        verify(newsArticlesRepository, times(1)).getNewsArticles()
    }

    @Test
    fun getNewsArticles_ShouldReturnDataFromRepository() {
        val newsArticle = NewsArticleFactory.makeNewsArticle()
        `when`(newsArticlesRepository.getNewsArticles()).thenReturn(Single.just(listOf(newsArticle)))

        val testObserver = getNewsArticlesUseCase.execute().test()

        testObserver.assertValue(listOf(newsArticle))
    }

}