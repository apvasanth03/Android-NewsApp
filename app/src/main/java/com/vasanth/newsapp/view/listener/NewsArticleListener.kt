package com.vasanth.newsapp.view.listener

import com.vasanth.presentation.model.NewsArticleUIModel

interface NewsArticleListener {

    fun onNewsArticleClicked(newsArticle: NewsArticleUIModel)

}