package com.vasanth.domain.test.factory

import com.vasanth.domain.model.NewsArticle

object NewsArticleFactory {

    fun makeNewsArticle(): NewsArticle {
        return NewsArticle(
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomDate(),
            DataFactory.randomString(),
            DataFactory.randomString()
        )
    }

}