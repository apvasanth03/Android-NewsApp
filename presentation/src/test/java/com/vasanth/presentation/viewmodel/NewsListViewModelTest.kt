package com.vasanth.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.vasanth.domain.interactor.browse.GetNewsArticlesUseCase
import com.vasanth.domain.model.NewsArticle
import com.vasanth.presentation.mapper.NewsArticleUIMapper
import com.vasanth.presentation.test.factory.NewsArticleFactory
import com.vasanth.presentation.test.factory.NewsArticleUIModelFactory
import io.reactivex.observers.DisposableObserver
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getNewsArticlesUseCase: GetNewsArticlesUseCase
    @Mock
    lateinit var mapper: NewsArticleUIMapper

    @Captor
    lateinit var captor: ArgumentCaptor<DisposableObserver<List<NewsArticle>>>

    @Test
    fun fetchNewsArticles_ShouldExecuteGetNewsArticlesUseCase() {
        NewsListViewModel(getNewsArticlesUseCase, mapper)

        verify(getNewsArticlesUseCase, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchNewsArticles_WhenUseCaseReturnsSuccess_ShouldSendSuccessToView() {
        val newsArticles = NewsArticleFactory.makeNewsArticle()
        val newsArticleUIModels = NewsArticleUIModelFactory.makeNewsArticleUIModel()
        `when`(mapper.mapToView(newsArticles)).thenReturn(newsArticleUIModels)

        val viewModel = NewsListViewModel(getNewsArticlesUseCase, mapper)

        verify(getNewsArticlesUseCase).execute(capture(captor), eq(null))
        captor.firstValue.onNext(listOf(newsArticles))

        Assert.assertThat(viewModel.getViewState().value, `is`(equalTo(NewsListViewModel.NewsListViewState.DATA)))
        Assert.assertThat(viewModel.getNewsArticles().value, `is`(equalTo(listOf(newsArticleUIModels))))
    }

    @Test
    fun fetchNewsArticles_WhenUseCaseReturnsError_ShouldSendErrorToView() {
        val viewModel = NewsListViewModel(getNewsArticlesUseCase, mapper)

        verify(getNewsArticlesUseCase).execute(capture(captor), eq(null))
        captor.firstValue.onError(Exception())

        Assert.assertThat(viewModel.getViewState().value, `is`(equalTo(NewsListViewModel.NewsListViewState.ERROR)))
    }

}