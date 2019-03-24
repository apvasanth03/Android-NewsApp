package com.vasanth.remote.mapper

import com.vasanth.remote.test.factory.NewsArticleModelFactory
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsArticleModelMapperTest {

    private val mapper = NewsArticleModelMapper()

    @Test
    fun mapFromModel_givenCorrectModel_shouldParseCorrectEntity() {
        val model = NewsArticleModelFactory.makeNewsArticleModel()

        val entity = mapper.mapFromModel(model)

        assertThat(entity.title, `is`(equalTo(model.title)))
        assertThat(entity.description, `is`(equalTo(model.description)))
        assertThat(entity.author, `is`(equalTo(model.author)))
        assertThat(entity.source, `is`(equalTo(model.source.name)))
        assertThat(entity.url, `is`(equalTo(model.url)))
        assertThat(entity.urlToImage, `is`(equalTo(model.urlToImage)))

    }

}