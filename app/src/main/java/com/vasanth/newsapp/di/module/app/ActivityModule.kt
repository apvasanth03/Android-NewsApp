package com.vasanth.newsapp.di.module.app

import com.vasanth.newsapp.di.module.news.NewsListModule
import com.vasanth.newsapp.di.scope.ActivityScope
import com.vasanth.newsapp.view.activity.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun contributesNewsListActivity(): NewsListActivity

}