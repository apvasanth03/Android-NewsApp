package com.vasanth.presentation.test.util

import com.vasanth.presentation.util.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * 1. A Class provides "SchedulerProvider" implementation for our TestApplication.
 * 2. We use CurrentThreadScheduler - Hence all our operation runs in same thread serially.
 */
class TestSchedulerProvider: SchedulerProvider{

    override fun uiScheduler(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun backgroundScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}