package com.vasanth.newsapp.util

import com.vasanth.presentation.util.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * A Class provides "SchedulerProvider" implementation for our application.
 */
class AppSchedulerProvider @Inject constructor(): SchedulerProvider{

    override fun uiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun backgroundScheduler(): Scheduler {
        return Schedulers.io()
    }
}