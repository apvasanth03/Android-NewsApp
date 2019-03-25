package com.vasanth.newsapp.di.module.news

import androidx.lifecycle.ViewModel
import com.vasanth.newsapp.di.module.app.ViewModelKey
import com.vasanth.presentation.viewmodel.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsListModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindNewsListViewModel(newsListViewModel: NewsListViewModel): ViewModel

}