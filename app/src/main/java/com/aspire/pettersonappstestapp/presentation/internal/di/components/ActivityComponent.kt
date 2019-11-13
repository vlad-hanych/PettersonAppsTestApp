package com.aspire.pettersonappstestapp.presentation.internal.di.components

import android.app.Activity
import com.aspire.pettersonappstestapp.presentation.internal.di.PerActivity
import com.aspire.pettersonappstestapp.presentation.internal.di.modules.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): Activity
}