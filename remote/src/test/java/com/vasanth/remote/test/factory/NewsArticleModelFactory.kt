package com.vasanth.remote.test.factory

import com.vasanth.remote.model.NewsArticleModel

object NewsArticleModelFactory {

    const val PUBLISHED_AT_TEST_VALUE = "2019-03-24T09:33:14Z"

    fun makeNewsArticleModel(): NewsArticleModel {
        return NewsArticleModel(
            SourceModelFactory.makeSourceModel(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            PUBLISHED_AT_TEST_VALUE,
            DataFactory.randomString()
        )
    }

}