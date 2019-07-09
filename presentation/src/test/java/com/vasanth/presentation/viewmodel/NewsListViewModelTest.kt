package com.vasanth.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vasanth.domain.usecase.GetNewsArticlesUseCase
import com.vasanth.httpclient.HttpClientException
import com.vasanth.presentation.mapper.NewsArticleUIMapper
import com.vasanth.presentation.test.factory.NewsArticleFactory
import com.vasanth.presentation.test.factory.NewsArticleUIModelFactory
import com.vasanth.presentation.test.util.TestSchedulerProvider
import com.vasanth.presentation.util.SchedulerProvider
import io.reactivex.Single
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getNewsArticlesUseCase: GetNewsArticlesUseCase
    @Mock
    lateinit var mapper: NewsArticleUIMapper

    private val schedulerProvider: SchedulerProvider = TestSchedulerProvider()

    @Test
    fun fetchNewsArticles_WhenUseCaseReturnsSuccess_ShouldSendSuccessToView() {
        val newsArticle = NewsArticleFactory.makeNewsArticle()
        val newsArticleUIModel = NewsArticleUIModelFactory.makeNewsArticleUIModel()
        `when`(getNewsArticlesUseCase.execute()).thenReturn(Single.just(listOf(newsArticle)))
        `when`(mapper.mapToView(newsArticle)).thenReturn(newsArticleUIModel)

        val viewModel = NewsListViewModel(getNewsArticlesUseCase, mapper, schedulerProvider)

        Assert.assertThat(viewModel.viewStateObservable.value, `is`(equalTo(NewsListViewModel.NewsListViewState.DATA)))
        Assert.assertThat(viewModel.newsArticlesObservable.value, `is`(equalTo(listOf(newsArticleUIModel))))
    }

    @Test
    fun fetchNewsArticles_WhenUseCaseReturnsError_ShouldSendErrorToView() {
        val useCaseError = HttpClientException(HttpClientException.ErrorCode.NO_CONNECTION_ERROR, -1)
        `when`(getNewsArticlesUseCase.execute()).thenReturn(Single.error(useCaseError))

        val viewModel = NewsListViewModel(getNewsArticlesUseCase, mapper, schedulerProvider)

        Assert.assertThat(
            viewModel.viewStateObservable.value,
            `is`(equalTo(NewsListViewModel.NewsListViewState.NO_INTERNET))
        )
    }

    @Test
    fun onNewsArticleItemClicked_ShouldGoToNewsDetailScreen() {
        val newsArticle = NewsArticleFactory.makeNewsArticle()
        val newsArticleUIModel = NewsArticleUIModelFactory.makeNewsArticleUIModel()
        `when`(getNewsArticlesUseCase.execute()).thenReturn(Single.just(listOf(newsArticle)))
        `when`(mapper.mapToView(newsArticle)).thenReturn(newsArticleUIModel)

        val viewModel = NewsListViewModel(getNewsArticlesUseCase, mapper, schedulerProvider)
        viewModel.onNewsArticleItemClicked(newsArticleUIModel)

        Assert.assertThat(
            viewModel.goToNewsDetailScreenObservable.value?.peekContent(),
            `is`(equalTo(newsArticleUIModel))
        )
    }

}