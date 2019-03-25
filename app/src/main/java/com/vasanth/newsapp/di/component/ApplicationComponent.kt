package com.vasanth.newsapp.di.component

import android.app.Application
import com.vasanth.newsapp.app.NewsApplication
import com.vasanth.newsapp.di.module.app.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,
        PresentationModule::class,
        DataModule::class,
        CacheModule::class,
        RemoteModule::class,
        HttpClientModule::class,
        ActivityModule::class
    )
)
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent

    }

}