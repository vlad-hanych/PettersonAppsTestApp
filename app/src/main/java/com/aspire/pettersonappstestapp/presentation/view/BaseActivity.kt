package com.aspire.pettersonappstestapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aspire.pettersonappstestapp.App
import com.aspire.pettersonappstestapp.presentation.internal.di.components.AppComponent
import com.aspire.pettersonappstestapp.presentation.internal.di.modules.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return [com.fernandocejas.android10.sample.presentation.internal.di.components.ApplicationComponent]
     */
    protected val applicationComponent: AppComponent
        get() = App.instance.appComponent

    /**
     * Get an Activity module for dependency injection.
     *
     * @return [com.fernandocejas.android10.sample.presentation.internal.di.modules.ActivityModule]
     */
    protected val activityModule: ActivityModule
        get() = ActivityModule(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.applicationComponent.inject(this)
    }

    /**
     * Adds a [Fragment] to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val trans = supportFragmentManager.beginTransaction()
        trans.add(containerViewId, fragment)
        trans.commit()
    }
}