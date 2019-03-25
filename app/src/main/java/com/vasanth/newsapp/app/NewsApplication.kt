package com.vasanth.newsapp.app

import android.app.Activity
import android.app.Application
import com.vasanth.newsapp.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Application class for our app.
 *
 * @author Vasanth
 */
class NewsApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    // Application Methods.
    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    // HasActivityInjector Methods.
    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }
}