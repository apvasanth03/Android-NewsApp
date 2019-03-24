package com.vasanth.remote.mapper

import com.vasanth.data.model.NewsArticleEntity
import com.vasanth.remote.model.NewsArticleModel
import com.vasanth.remote.util.DateUtil
import java.util.*
import javax.inject.Inject

class NewsArticleModelMapper @Inject constructor() : ModelMapper<NewsArticleModel, NewsArticleEntity> {

    companion object {
        const val PUBLISHED_AT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
    }

    override fun mapFromModel(model: NewsArticleModel): NewsArticleEntity {
        val publishedAtDate = DateUtil.convertStringToDate(model.publishedAt, PUBLISHED_AT_DATE_FORMAT) ?: Date()
        return NewsArticleEntity(
            model.title,
            model.description,
            model.author,
            model.source.name,
            publishedAtDate,
            model.url,
            model.urlToImage
        )
    }
}