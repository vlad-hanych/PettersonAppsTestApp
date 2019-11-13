package com.aspire.pettersonappstestapp.data.repositories.datasources

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceFactory @Inject
internal constructor(private val context: ServerDataSource,
                     private val serverDataSource: ServerDataSource) {
    fun create(condition: Boolean): DataSource {
        val dataSource: DataSource? = when (condition) {
            true -> serverDataSource
            false -> null
            else -> {
                serverDataSource
            }
        }
        return dataSource!!
    }
}