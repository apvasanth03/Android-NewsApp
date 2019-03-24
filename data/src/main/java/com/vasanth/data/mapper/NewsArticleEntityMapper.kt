package com.vasanth.data.mapper

import com.vasanth.data.model.NewsArticleEntity
import com.vasanth.domain.model.NewsArticle
import javax.inject.Inject

open class NewsArticleEntityMapper @Inject constructor() : EntityMapper<NewsArticleEntity, NewsArticle> {

    override fun mapFromEntity(entity: NewsArticleEntity): NewsArticle {
        return NewsArticle(
            entity.title,
            entity.description,
            entity.author,
            entity.source,
            entity.publishedAt,
            entity.url,
            entity.urlToImage
        )
    }
}