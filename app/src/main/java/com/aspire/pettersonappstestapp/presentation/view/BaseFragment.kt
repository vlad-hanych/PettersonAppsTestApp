package com.aspire.pettersonappstestapp.presentation.view

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aspire.pettersonappstestapp.presentation.internal.di.HasComponent

abstract class BaseFragment : Fragment() {
    /**
     * Shows a [android.widget.Toast] message.
     *
     * @param message An string representing a message to be shown.
     */
    protected fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).getComponent())!!
    }
}