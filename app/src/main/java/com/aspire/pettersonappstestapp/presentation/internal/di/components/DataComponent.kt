package com.aspire.pettersonappstestapp.presentation.internal.di.components

import com.aspire.pettersonappstestapp.presentation.internal.di.PerActivity
import com.aspire.pettersonappstestapp.presentation.internal.di.modules.ActivityModule
import com.aspire.pettersonappstestapp.presentation.internal.di.modules.DataModule
import com.aspire.pettersonappstestapp.presentation.view.DataFragment
import dagger.Component

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, DataModule::class]
)
interface DataComponent : ActivityComponent {
    fun inject(userListFragment: DataFragment)
}