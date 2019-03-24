package com.vasanth.presentation.test.factory

import com.vasanth.presentation.model.NewsArticleUIModel

object NewsArticleUIModelFactory {

    fun makeNewsArticleUIModel(): NewsArticleUIModel {
        return NewsArticleUIModel(
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString()
        )
    }

}