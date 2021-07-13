package labs.gas.musical.core.threads.extensions

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import labs.gas.musical.core.threads.Scheduler

fun <T> Observable<T>.runOnIo(scheduler: Scheduler): Observable<T> =
    subscribeOn(scheduler.io()).observeOn(scheduler.ui())

fun <T> Single<T>.runOnIo(scheduler: Scheduler): Single<T> =
    subscribeOn(scheduler.io()).observeOn(scheduler.ui())

fun <T> Flowable<T>.runOnIo(scheduler: Scheduler): Flowable<T> =
    subscribeOn(scheduler.io()).observeOn(scheduler.ui())

fun Completable.runOnIo(scheduler: Scheduler): Completable =
    subscribeOn(scheduler.io()).observeOn(scheduler.ui())
