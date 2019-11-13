package com.aspire.pettersonappstestapp.data.repositories.datasources

import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.substances.CatFactDataRaw
import rx.Observable

interface DataSource {
    fun catFact(): Observable<CatFactDataRaw>
    fun breedDataList(): Observable<BreedDataRaw>
}