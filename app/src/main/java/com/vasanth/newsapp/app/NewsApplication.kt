package com.vasanth.newsapp.app

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.vasanth.newsapp.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Application class for our app.
 *
 * @author Vasanth
 */
class NewsApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    // Application Methods.
    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    // HasActivityInjector Methods.
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    // HasSupportFragmentInjector Methods.
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}