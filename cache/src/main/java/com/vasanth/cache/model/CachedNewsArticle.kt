package com.vasanth.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vasanth.cache.db.NewsArticleDBConstants
import java.util.*

@Entity(tableName = NewsArticleDBConstants.TABLE_NAME)
class CachedNewsArticle(
    @ColumnInfo(name = NewsArticleDBConstants.COLUMN_TITLE) val title: String,
    @ColumnInfo(name = NewsArticleDBConstants.COLUMN_DESCRIPTION) val description: String,
    @ColumnInfo(name = NewsArticleDBConstants.COLUMN_AUTHOR) val author: String,
    @ColumnInfo(name = NewsArticleDBConstants.COLUMN_SOURCE) val source: String,
    @ColumnInfo(name = NewsArticleDBConstants.COLUMN_PUBLISHED_AT) val publishedAt: Date,
    @ColumnInfo(name = NewsArticleDBConstants.COLUMN_URL) val url: String,
    @ColumnInfo(name = NewsArticleDBConstants.COLUMN_URL_TO_IMAGE) val urlToImage: String
) {
    @ColumnInfo(name = NewsArticleDBConstants.COLUMN_ID)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}