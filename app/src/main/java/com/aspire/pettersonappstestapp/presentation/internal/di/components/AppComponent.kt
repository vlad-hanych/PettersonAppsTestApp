package com.aspire.pettersonappstestapp.presentation.internal.di.components

import android.content.Context
import com.aspire.pettersonappstestapp.domain.DataRepository
import com.aspire.pettersonappstestapp.presentation.internal.di.modules.AppModule
import com.aspire.pettersonappstestapp.presentation.internal.di.modules.RetrofitModule
import com.aspire.pettersonappstestapp.presentation.view.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class])
interface AppComponent {
    fun inject(baseActivity: BaseActivity)

    fun context(): Context
    fun dataRepository(): DataRepository
}