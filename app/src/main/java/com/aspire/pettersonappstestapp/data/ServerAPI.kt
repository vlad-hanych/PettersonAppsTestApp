package com.aspire.pettersonappstestapp.data

import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.substances.CatFactDataRaw
import retrofit2.http.*
import rx.Observable

interface ServerAPI {
    @GET("/fact?max_length=200")
    fun getCatFact(): Observable<CatFactDataRaw>

    @GET("/breeds?limit=1000")
    fun getBreedsList(/*@Query("id") id: String*/): Observable<BreedDataRaw>
}