package labs.gas.musical.core.threads

import io.reactivex.rxjava3.core.Scheduler

interface Scheduler {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun newThread(): Scheduler
    fun ui(): Scheduler
}
