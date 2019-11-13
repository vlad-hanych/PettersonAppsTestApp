package com.aspire.pettersonappstestapp.domain.interactor

import rx.Observable
import rx.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers

abstract class UseCase<T, Params> {
    internal abstract fun buildUseCaseObservable(params: Params?): Observable<T>

    fun execute(params: Params?): Observable <T> {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        return observable
    }
}