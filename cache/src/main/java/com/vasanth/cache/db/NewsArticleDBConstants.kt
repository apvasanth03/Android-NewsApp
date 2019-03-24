package com.vasanth.cache.db

object NewsArticleDBConstants {

    const val DATABASE_NAME = "news_articles.db"

    const val TABLE_NAME = "news_articles"

    const val COLUMN_ID = "id"
    const val COLUMN_TITLE = "title"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_AUTHOR = "author"
    const val COLUMN_SOURCE = "source"
    const val COLUMN_PUBLISHED_AT = "published_at"
    const val COLUMN_URL = "url"
    const val COLUMN_URL_TO_IMAGE = "url_to_image"

    const val QUERY_NEWS_ARTICLES = "SELECT * FROM $TABLE_NAME"
    const val QUERY_NEWS_ARTICLES_COUNT = "SELECT COUNT(*) FROM $TABLE_NAME"
    const val DELETE_NEWS_ARTICLES = "DELETE FROM $TABLE_NAME"

}