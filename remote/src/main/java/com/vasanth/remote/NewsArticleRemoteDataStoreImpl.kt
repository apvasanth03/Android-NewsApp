package com.vasanth.remote

import com.vasanth.data.model.NewsArticleEntity
import com.vasanth.data.repository.NewsArticleRemoteDataStore
import com.vasanth.httpclient.HttpClient
import com.vasanth.httpclient.HttpClientException
import com.vasanth.httpclient.HttpResponseListener
import com.vasanth.remote.mapper.NewsArticleModelMapper
import com.vasanth.remote.model.NewsArticleResponseModel
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import javax.inject.Inject

/**
 * A Class provides implementation for NewsArticleRemoteDataStore.
 *
 * @author Vasanth
 */
class NewsArticleRemoteDataStoreImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val mapper: NewsArticleModelMapper
) :
    NewsArticleRemoteDataStore {

    companion object {
        private const val NEWS_API_KEY = ""
        private const val GET_NEWS_ARTICLES_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=$NEWS_API_KEY"
    }

    // NewsArticleRemote Methods.
    override fun getNewsArticles(): Single<List<NewsArticleEntity>> {
        return Single.create(SingleOnSubscribe { emitter ->
            httpClient.getRequest(GET_NEWS_ARTICLES_URL, null, NewsArticleResponseModel::class.java,
                object : HttpResponseListener<NewsArticleResponseModel> {
                    override fun onSuccessResponse(response: NewsArticleResponseModel) {
                        val newsArticleEntities = response.articles.map { mapper.mapFromModel(it) }
                        emitter.onSuccess(newsArticleEntities)
                    }

                    override fun onErrorResponse(exception: HttpClientException) {
                        emitter.onError(exception)
                    }
                }
            )
        })
    }
}