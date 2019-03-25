package com.vasanth.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsArticleUIModel(
    val title: String,
    val source: String,
    val publishedAt: String,
    val url: String,
    val urlToImage: String
) : Parcelable