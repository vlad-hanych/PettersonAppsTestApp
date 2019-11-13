package com.aspire.pettersonappstestapp.presentation.view

import com.aspire.pettersonappstestapp.substances.BreedDataRaw

interface DataView: AbstrView {
    fun needToGetCatFact()
    fun onGotCatFact(text: String)
    fun onCatFactError(errorMessage: String)

    fun needToGetBreedsList()
    fun onGotBreedsList(rawData: BreedDataRaw)
    fun onBreedsListError(errorMessage: String)
}