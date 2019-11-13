package com.aspire.pettersonappstestapp.presentation.internal.di.modules

import android.app.Application
import android.content.Context
import com.aspire.pettersonappstestapp.domain.DataRepository
import com.aspire.pettersonappstestapp.data.repositories.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDataRepository(dataRepositoryImp: DataRepositoryImpl): DataRepository {
        return dataRepositoryImp
    }
}