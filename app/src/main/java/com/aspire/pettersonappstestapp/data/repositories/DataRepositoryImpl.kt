package com.aspire.pettersonappstestapp.data.repositories

import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.domain.DataRepository
import com.aspire.pettersonappstestapp.substances.CatFactDataRaw
import com.aspire.pettersonappstestapp.data.repositories.datasources.DataSource
import com.aspire.pettersonappstestapp.data.repositories.datasources.DataSourceFactory
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject
internal constructor(private val dataSourceFactory: DataSourceFactory):
    DataRepository {
    override fun catFact(): Observable<CatFactDataRaw> {
        val serverDataSource: DataSource = this.dataSourceFactory.create(true)

        return serverDataSource.catFact()
    }

    override fun breedDataList(): Observable<BreedDataRaw> {
    val serverDataSource: DataSource = this.dataSourceFactory.create(true)

    return serverDataSource.breedDataList()
    }
}