package com.vasanth.domain.model

import java.util.*

data class NewsArticle(
    val title: String, val description: String, val author: String, val source: String,
    val publishedAt: Date, val url: String, val urlToImage: String
)