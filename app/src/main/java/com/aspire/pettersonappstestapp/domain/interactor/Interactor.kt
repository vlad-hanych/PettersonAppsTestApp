package com.aspire.pettersonappstestapp.domain.interactor

import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.domain.interactor.usecases.GetBreedsList
import com.aspire.pettersonappstestapp.domain.interactor.usecases.GetCatFact
import com.aspire.pettersonappstestapp.substances.CatFactDataRaw
import rx.Observable

import javax.inject.Inject

class Interactor @Inject constructor(private val getCatFactUseCase: GetCatFact,
                                     private val getBreedsListUseCase: GetBreedsList) {
    fun getCatFact(): Observable<CatFactDataRaw> {
        return getCatFactUseCase.execute(null)
    }

    fun getBreedsList(): Observable<BreedDataRaw> {
        return getBreedsListUseCase.execute(null)
    }
}