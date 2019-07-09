package com.vasanth.presentation.util

import io.reactivex.Scheduler

/**
 * 1. A Protocol abstracts the actual scheduler used for running the task.
 * 2. Which helps us in unit testing.
 */
interface SchedulerProvider {

    fun uiScheduler(): Scheduler

    fun backgroundScheduler(): Scheduler
}