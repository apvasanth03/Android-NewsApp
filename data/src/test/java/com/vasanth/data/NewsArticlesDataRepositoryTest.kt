package com.vasanth.data

import com.nhaarman.mockitokotlin2.any
import com.vasanth.data.mapper.NewsArticleEntityMapper
import com.vasanth.data.repository.NewsArticleCacheDataStore
import com.vasanth.data.repository.NewsArticleRemoteDataStore
import com.vasanth.data.test.factory.NewsArticleEntityFactory
import com.vasanth.data.test.factory.NewsArticleFactory
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsArticlesDataRepositoryTest {

    @Mock
    lateinit var remoteDataStore: NewsArticleRemoteDataStore
    @Mock
    lateinit var cacheDataStore: NewsArticleCacheDataStore
    @Mock
    lateinit var mapper: NewsArticleEntityMapper
    @InjectMocks
    lateinit var repository: NewsArticlesDataRepository


    @Test
    fun getNewsArticles_WhenCacheDateIsNotAvailable_shouldGetFromRemoteSource() {
        val newsArticleEntity = NewsArticleEntityFactory.makeNewsArticleEntity()
        val newsArticle = NewsArticleFactory.makeNewsArticle()
        `when`(cacheDataStore.areNewsArticlesCached()).thenReturn(Single.just(false))
        `when`(cacheDataStore.clearNewsArticles()).thenReturn(Completable.complete())
        `when`(cacheDataStore.saveNewsArticles(any())).thenReturn(Completable.complete())
        `when`(remoteDataStore.getNewsArticles()).thenReturn(Single.just(listOf(newsArticleEntity)))
        `when`(mapper.mapFromEntity(newsArticleEntity)).thenReturn(newsArticle)

        val testObserver = repository.getNewsArticles().test()

        testObserver.assertValue(listOf(newsArticle))
    }

    @Test
    fun getNewsArticles_WhenCacheDateIsAvailable_shouldGetFromCacheSource() {
        val newsArticleEntity = NewsArticleEntityFactory.makeNewsArticleEntity()
        val newsArticle = NewsArticleFactory.makeNewsArticle()
        `when`(cacheDataStore.areNewsArticlesCached()).thenReturn(Single.just(true))
        `when`(cacheDataStore.getNewsArticles()).thenReturn(Observable.just(listOf(newsArticleEntity)))
        `when`(cacheDataStore.clearNewsArticles()).thenReturn(Completable.complete())
        `when`(cacheDataStore.saveNewsArticles(any())).thenReturn(Completable.complete())
        `when`(remoteDataStore.getNewsArticles()).thenReturn(Single.just(listOf()))
        `when`(mapper.mapFromEntity(newsArticleEntity)).thenReturn(newsArticle)

        val testObserver = repository.getNewsArticles().test()

        testObserver.assertValue(listOf(newsArticle))
    }

}