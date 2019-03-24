package com.vasanth.cache.test.factory

import com.vasanth.cache.model.CachedNewsArticle

object CachedNewsArticleFactory {

    fun makeCachedNewsArticle(): CachedNewsArticle {
        return CachedNewsArticle(
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