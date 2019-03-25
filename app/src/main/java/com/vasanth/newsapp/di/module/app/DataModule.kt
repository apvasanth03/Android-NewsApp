package com.vasanth.newsapp.di.module.app

import com.vasanth.data.NewsArticlesDataRepository
import com.vasanth.domain.repository.NewsArticlesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(newsArticlesDataRepository: NewsArticlesDataRepository): NewsArticlesRepository

}