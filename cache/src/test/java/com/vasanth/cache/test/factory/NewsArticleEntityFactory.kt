package com.vasanth.cache.test.factory

import com.vasanth.data.model.NewsArticleEntity

object NewsArticleEntityFactory {

    fun makeNewsArticleEntity(): NewsArticleEntity {
        return NewsArticleEntity(
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