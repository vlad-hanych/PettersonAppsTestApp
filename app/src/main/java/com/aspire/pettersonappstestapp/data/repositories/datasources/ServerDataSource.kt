package com.aspire.pettersonappstestapp.data.repositories.datasources

import com.aspire.pettersonappstestapp.data.ServerAPI
import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.substances.CatFactDataRaw
import rx.Observable
import javax.inject.Inject

class ServerDataSource
@Inject
internal constructor(private val serverAPI: ServerAPI) : DataSource {

    override fun catFact(): Observable<CatFactDataRaw> {
        return serverAPI.getCatFact()
    }

    override fun breedDataList(): Observable<BreedDataRaw> {
        return serverAPI.getBreedsList()
    }

}