package com.vasanth.domain.executor

import io.reactivex.Scheduler

/**
 * A interface defines the abstraction over the PostExecutionThread.
 *
 * @author
 */
interface PostExecutionThread {

    val scheduler: Scheduler

}
