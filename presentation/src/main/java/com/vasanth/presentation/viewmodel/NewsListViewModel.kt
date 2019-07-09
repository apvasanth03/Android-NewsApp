package com.vasanth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vasanth.domain.usecase.GetNewsArticlesUseCase
import com.vasanth.httpclient.HttpClientException
import com.vasanth.presentation.mapper.NewsArticleUIMapper
import com.vasanth.presentation.model.NewsArticleUIModel
import com.vasanth.presentation.util.Event
import com.vasanth.presentation.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase,
    private val mapper: NewsArticleUIMapper,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    // Inner Types.
    enum class NewsListViewState {
        LOADING,
        DATA,
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
    private val goToNewsDetailScreen: MutableLiveData<Event<NewsArticleUIModel>> by lazy {
        MutableLiveData<Event<NewsArticleUIModel>>()
    }

    private val disposables: CompositeDisposable = CompositeDisposable()

    // View Observable Properties.
    val viewStateObservable: LiveData<NewsListViewState> get() = viewState
    val newsArticlesObservable: LiveData<List<NewsArticleUIModel>> get() = newsArticles
    val goToNewsDetailScreenObservable: LiveData<Event<NewsArticleUIModel>> = goToNewsDetailScreen


    // Init.
    init {
        fetchNewsArticles()
    }

    // ViewModel Methods.
    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    // Public Methods.
    fun onNewsArticleItemClicked(newsArticle: NewsArticleUIModel) {
        goToNewsDetailScreen.value = Event(newsArticle)
    }

    // Private Methods.
    private fun fetchNewsArticles() {
        viewState.value = NewsListViewState.LOADING
        val disposable = getNewsArticlesUseCase.execute()
            .map { newsArticles ->
                return@map newsArticles.map { mapper.mapToView(it) }
            }
            .subscribeOn(schedulerProvider.backgroundScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribe(this::fetchNewsArticlesSuccess, this::fetchNewsArticlesError)
        disposables.add(disposable)
    }

    private fun fetchNewsArticlesSuccess(articles: List<NewsArticleUIModel>) {
        viewState.value = NewsListViewState.DATA
        newsArticles.value = articles
    }

    private fun fetchNewsArticlesError(e: Throwable) {
        if (e is HttpClientException && e.errorCode == HttpClientException.ErrorCode.NO_CONNECTION_ERROR) {
            viewState.value = NewsListViewState.NO_INTERNET
        } else {
            viewState.value = NewsListViewState.ERROR
        }
    }
}