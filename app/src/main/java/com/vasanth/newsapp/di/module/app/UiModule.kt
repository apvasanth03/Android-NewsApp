package com.vasanth.newsapp.di.module.app

import com.vasanth.newsapp.util.AppSchedulerProvider
import com.vasanth.presentation.util.SchedulerProvider
import dagger.Binds
import dagger.Module

@Module
abstract class UiModule {

    @Binds
    abstract fun bindSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider

}