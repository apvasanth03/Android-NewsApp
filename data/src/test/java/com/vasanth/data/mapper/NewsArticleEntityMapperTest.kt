package com.vasanth.data.mapper

import com.vasanth.data.test.factory.NewsArticleEntityFactory
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsArticleEntityMapperTest {

    private val mapper = NewsArticleEntityMapper()

    @Test
    fun mapFromEntity_GivenCorrectEntityModel_ShouldParseCorrectDomainModel() {
        val entity = NewsArticleEntityFactory.makeNewsArticleEntity()

        val domain = mapper.mapFromEntity(entity)

        Assert.assertThat(domain.title, Matchers.`is`(Matchers.equalTo(entity.title)))
        Assert.assertThat(domain.description, Matchers.`is`(Matchers.equalTo(entity.description)))
        Assert.assertThat(domain.author, Matchers.`is`(Matchers.equalTo(entity.author)))
        Assert.assertThat(domain.source, Matchers.`is`(Matchers.equalTo(entity.source)))
        Assert.assertThat(domain.publishedAt, Matchers.`is`(Matchers.equalTo(entity.publishedAt)))
        Assert.assertThat(domain.url, Matchers.`is`(Matchers.equalTo(entity.url)))
        Assert.assertThat(domain.urlToImage, Matchers.`is`(Matchers.equalTo(entity.urlToImage)))
    }

}