package com.vasanth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vasanth.domain.interactor.browse.GetNewsArticlesUseCase
import com.vasanth.domain.model.NewsArticle
import com.vasanth.httpclient.HttpClientException
import com.vasanth.presentation.mapper.NewsArticleUIMapper
import com.vasanth.presentation.model.NewsArticleUIModel
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase,
    private val mapper: NewsArticleUIMapper
) : ViewModel() {

    // Inner Types.
    enum class NewsListViewState {
        LOADING,
        DATA,
        EMPTY,
        NO_INTERNET,
        ERROR
    }

    // Properties.
    private val viewState: MutableLiveData<NewsListViewState> by lazy {
        MutableLiveData<NewsListViewState>()
    }
    private val newsArticles: MutableLiveData<List<NewsArticleUIModel>> by lazy {
        MutableLiveData<List<NewsArticleUIModel>>()
    }
    private val goToNewsDetailScreen: MutableLiveData<NewsArticleUIModel> by lazy {
        MutableLiveData<NewsArticleUIModel>()
    }

    // View Observable Properties.
    val viewStateObservable: LiveData<NewsListViewState> get() = viewState
    val newsArticlesObservable: LiveData<List<NewsArticleUIModel>> get() = newsArticles
    val goToNewsDetailScreenObservable: LiveData<NewsArticleUIModel> = goToNewsDetailScreen


    // Init.
    init {
        fetchNewsArticles()
    }

    // ViewModel Methods.
    override fun onCleared() {
        getNewsArticlesUseCase.dispose()
        super.onCleared()
    }

    // Public Methods.
    fun onNewsArticleItemClicked(newsArticle: NewsArticleUIModel) {
        goToNewsDetailScreen.value = newsArticle
    }

    // Private Methods.
    private fun fetchNewsArticles() {
        viewState.value = NewsListViewState.LOADING
        getNewsArticlesUseCase.execute(FetchNewsArticlesSubscriber())
    }

    inner class FetchNewsArticlesSubscriber : DisposableObserver<List<NewsArticle>>() {
        override fun onComplete() {

        }

        override fun onNext(articles: List<NewsArticle>) {
            if (articles.isEmpty()) {
                viewState.value = NewsListViewState.EMPTY
            } else {
                viewState.value = NewsListViewState.DATA
            }
            newsArticles.value = articles.map { mapper.mapToView(it) }
        }

        override fun onError(e: Throwable) {
            if (e is HttpClientException && e.errorCode == HttpClientException.ErrorCode.NO_CONNECTION_ERROR) {
                viewState.value = NewsListViewState.NO_INTERNET
            } else {
                viewState.value = NewsListViewState.ERROR
            }
        }
    }

}