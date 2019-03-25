package com.vasanth.newsapp.di.module.app

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}