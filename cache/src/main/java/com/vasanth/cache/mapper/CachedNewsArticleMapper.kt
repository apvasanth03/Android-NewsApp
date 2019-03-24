package com.vasanth.cache.mapper

import com.vasanth.cache.model.CachedNewsArticle
import com.vasanth.data.model.NewsArticleEntity
import javax.inject.Inject

class CachedNewsArticleMapper @Inject constructor() : CacheMapper<CachedNewsArticle, NewsArticleEntity> {

    override fun mapFromCached(cache: CachedNewsArticle): NewsArticleEntity {
        return NewsArticleEntity(
            cache.title, cache.description, cache.author,
            cache.source, cache.publishedAt, cache.url, cache.urlToImage
        )
    }

    override fun mapToCached(entity: NewsArticleEntity): CachedNewsArticle {
        return CachedNewsArticle(
            entity.title, entity.description, entity.author,
            entity.source, entity.publishedAt, entity.url, entity.urlToImage
        )
    }
}