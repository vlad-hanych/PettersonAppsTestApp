package com.aspire.pettersonappstestapp.presentation.presenter.implementations

import android.util.Log
import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.domain.interactor.Interactor
import com.aspire.pettersonappstestapp.presentation.internal.di.PerActivity
import com.aspire.pettersonappstestapp.substances.CatFactDataRaw
import com.aspire.pettersonappstestapp.presentation.presenter.BasePresenter
import com.aspire.pettersonappstestapp.presentation.presenter.abstractions.DataPresenter
import com.aspire.pettersonappstestapp.presentation.view.DataView
import rx.Subscriber
import javax.inject.Inject
import java.util.*


@PerActivity
class DataPresenterImpl @Inject
internal constructor(private val interactor: Interactor) : BasePresenter<DataView>(),
    DataPresenter {
    override fun handleGettingCatFact() {
        this.interactor.getCatFact().subscribe(object : Subscriber<CatFactDataRaw>() {
            override fun onNext(obj: CatFactDataRaw?) {
                (view as DataView).onGotCatFact(obj!!.fact)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                (view as DataView).onCatFactError(e.localizedMessage!!)
            }
        })
    }

    override fun handleGettingBreedsList() {
        this.interactor.getBreedsList().subscribe(object : Subscriber<BreedDataRaw>() {
            override fun onNext(obj: BreedDataRaw?) {
                val list = obj!!.data

                Collections.sort(list
                ) { first, second -> first._breed.compareTo(second._breed) }

                (view as DataView).onGotBreedsList(obj)
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                (view as DataView).onBreedsListError(e.localizedMessage!!)
            }
        })
    }

    /* private inner class GetCatFactObserver : DefaultObserver<CatFactDataRaw>() {
         override fun onComplete() {

         }

         override fun onError(e: Throwable) {

         }

         override fun onNext(catFactData: CatFactDataRaw) {

         }
     }*/

}