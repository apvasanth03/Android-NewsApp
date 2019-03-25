package com.vasanth.newsapp.di.module.app

import com.vasanth.domain.executor.PostExecutionThread
import com.vasanth.newsapp.app.UiThread
import dagger.Binds
import dagger.Module

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}