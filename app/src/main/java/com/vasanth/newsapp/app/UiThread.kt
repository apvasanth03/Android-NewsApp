package com.vasanth.newsapp.app

import com.vasanth.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * A class provides implementation for PostExecutionThread
 *
 * @author Vasanth
 */
class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}