package com.aspire.pettersonappstestapp.presentation.internal.di

interface HasComponent<C> {
    fun getComponent(): C
}