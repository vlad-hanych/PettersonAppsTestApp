package com.aspire.pettersonappstestapp

import android.app.Application
import com.aspire.pettersonappstestapp.presentation.internal.di.components.AppComponent
import com.aspire.pettersonappstestapp.presentation.internal.di.components.DaggerAppComponent
import com.aspire.pettersonappstestapp.presentation.internal.di.modules.AppModule

class App: Application() {
    companion object {
        lateinit var instance: App private set
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this@App

        setupAppComponentDI()
    }

    private fun setupAppComponentDI() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(instance))
            .build()
    }
}