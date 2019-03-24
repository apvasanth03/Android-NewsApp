package com.vasanth.presentation.mapper

import com.vasanth.domain.model.NewsArticle
import com.vasanth.presentation.model.NewsArticleUIModel
import com.vasanth.presentation.util.DateUtil
import javax.inject.Inject

open class NewsArticleUIMapper @Inject constructor() : Mapper<NewsArticleUIModel, NewsArticle> {

    companion object {
        const val PUBLISHED_AT_DISPLAY_DATE_FORMAT = "MMM d, yyyy"
    }

    override fun mapToView(type: NewsArticle): NewsArticleUIModel {
        val publishedAtDisplayDate = DateUtil.convertDateToString(type.publishedAt, PUBLISHED_AT_DISPLAY_DATE_FORMAT)
        return NewsArticleUIModel(type.title, type.source, publishedAtDisplayDate, type.url, type.urlToImage)
    }
}