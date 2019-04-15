package com.vasanth.newsapp.di.module.app

import com.vasanth.newsapp.di.module.news.NewsListModule
import com.vasanth.newsapp.di.scope.FragmentScope
import com.vasanth.newsapp.view.fragment.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun contributesNewsListFragment(): NewsListFragment

}