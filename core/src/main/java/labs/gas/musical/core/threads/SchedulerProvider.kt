package labs.gas.musical.core.threads

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulerProvider : Scheduler {
    override fun io() = Schedulers.io()
    override fun computation() = Schedulers.computation()
    override fun newThread() = Schedulers.newThread()
    override fun ui() = AndroidSchedulers.mainThread()
}
