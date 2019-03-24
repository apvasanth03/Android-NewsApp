package com.vasanth.presentation.mapper

import com.vasanth.presentation.test.factory.NewsArticleFactory
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsArticleUIMapperTest {

    private val mapper = NewsArticleUIMapper()

    @Test
    fun mapToView_givenCorrectDomainModel_shouldParseCorrectUIModel() {
        val domainModel = NewsArticleFactory.makeNewsArticle()

        val uiModel = mapper.mapToView(domainModel)

        Assert.assertThat(uiModel.title, Matchers.`is`(Matchers.equalTo(domainModel.title)))
        Assert.assertThat(uiModel.source, Matchers.`is`(Matchers.equalTo(domainModel.source)))
        Assert.assertThat(uiModel.url, Matchers.`is`(Matchers.equalTo(domainModel.url)))
        Assert.assertThat(uiModel.urlToImage, Matchers.`is`(Matchers.equalTo(domainModel.urlToImage)))
    }

}