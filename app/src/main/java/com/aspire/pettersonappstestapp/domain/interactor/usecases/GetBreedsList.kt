package com.aspire.pettersonappstestapp.domain.interactor.usecases

import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.domain.interactor.UseCase
import com.aspire.pettersonappstestapp.domain.DataRepository

import rx.Observable
import javax.inject.Inject

class GetBreedsList @Inject constructor(private val dataRepository: DataRepository /*Abstraction is mandatory here for Dependency Rule*/) : UseCase<BreedDataRaw, Void>() {
    override fun buildUseCaseObservable(params: Void?): Observable<BreedDataRaw> {
        return dataRepository.breedDataList()
    }
}