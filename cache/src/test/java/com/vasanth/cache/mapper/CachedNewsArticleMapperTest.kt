package com.vasanth.cache.mapper

import com.vasanth.cache.test.factory.CachedNewsArticleFactory
import com.vasanth.cache.test.factory.NewsArticleEntityFactory
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CachedNewsArticleMapperTest {

    private val mapper = CachedNewsArticleMapper()

    @Test
    fun mapFromCached_givenCorrectCacheModel_shouldParseCorrectEntityModel() {
        val cache = CachedNewsArticleFactory.makeCachedNewsArticle()

        val entity = mapper.mapFromCached(cache)

        Assert.assertThat(entity.title, Matchers.`is`(Matchers.equalTo(cache.title)))
        Assert.assertThat(entity.description, Matchers.`is`(Matchers.equalTo(cache.description)))
        Assert.assertThat(entity.author, Matchers.`is`(Matchers.equalTo(cache.author)))
        Assert.assertThat(entity.source, Matchers.`is`(Matchers.equalTo(cache.source)))
        Assert.assertThat(entity.publishedAt, Matchers.`is`(Matchers.equalTo(cache.publishedAt)))
        Assert.assertThat(entity.url, Matchers.`is`(Matchers.equalTo(cache.url)))
        Assert.assertThat(entity.urlToImage, Matchers.`is`(Matchers.equalTo(cache.urlToImage)))
    }

    @Test
    fun mapToCached_givenCorrectEntityModel_shouldParseCorrectCacheModel() {
        val entity = NewsArticleEntityFactory.makeNewsArticleEntity()

        val cache = mapper.mapToCached(entity)

        Assert.assertThat(cache.title, Matchers.`is`(Matchers.equalTo(entity.title)))
        Assert.assertThat(cache.description, Matchers.`is`(Matchers.equalTo(entity.description)))
        Assert.assertThat(cache.author, Matchers.`is`(Matchers.equalTo(entity.author)))
        Assert.assertThat(cache.source, Matchers.`is`(Matchers.equalTo(entity.source)))
        Assert.assertThat(cache.publishedAt, Matchers.`is`(Matchers.equalTo(entity.publishedAt)))
        Assert.assertThat(cache.url, Matchers.`is`(Matchers.equalTo(entity.url)))
        Assert.assertThat(cache.urlToImage, Matchers.`is`(Matchers.equalTo(entity.urlToImage)))
    }

}