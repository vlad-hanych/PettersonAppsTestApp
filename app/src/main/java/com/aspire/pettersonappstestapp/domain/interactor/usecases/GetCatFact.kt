package com.aspire.pettersonappstestapp.domain.interactor.usecases

import com.aspire.pettersonappstestapp.domain.interactor.UseCase
import com.aspire.pettersonappstestapp.domain.DataRepository
import com.aspire.pettersonappstestapp.substances.CatFactDataRaw
import rx.Observable
import javax.inject.Inject

class GetCatFact @Inject constructor(private val dataRepository: DataRepository /*Abstraction is mandatory here for Dependency Rule*/) : UseCase<CatFactDataRaw, Void>() {
    override fun buildUseCaseObservable(params: Void?): Observable<CatFactDataRaw> {
        return dataRepository.catFact()
    }
}