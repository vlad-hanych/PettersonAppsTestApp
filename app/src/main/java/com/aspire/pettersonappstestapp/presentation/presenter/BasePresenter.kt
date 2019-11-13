package com.aspire.pettersonappstestapp.presentation.presenter

import com.aspire.pettersonappstestapp.presentation.view.AbstrView

abstract class BasePresenter<V : AbstrView> {

    var view: V? = null
        private set

    fun attachView(v: V) {
        this.view = v
    }

    fun cleanView() {
        view = null
    }
}