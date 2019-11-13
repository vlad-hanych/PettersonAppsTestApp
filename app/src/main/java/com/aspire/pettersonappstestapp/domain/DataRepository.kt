package com.aspire.pettersonappstestapp.domain

import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.substances.CatFactDataRaw
import rx.Observable

interface DataRepository {

    fun catFact(): Observable<CatFactDataRaw>

    fun breedDataList(): Observable<BreedDataRaw>
}