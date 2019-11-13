package com.aspire.pettersonappstestapp.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.aspire.pettersonappstestapp.R
import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import com.aspire.pettersonappstestapp.presentation.BreedsAdapter
import com.aspire.pettersonappstestapp.presentation.internal.di.components.DataComponent
import com.aspire.pettersonappstestapp.presentation.presenter.implementations.DataPresenterImpl
import javax.inject.Inject
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DataFragment: BaseFragment(), DataView {
    private val downloadedDataKey: String = "downloaded_data"

    private val filteredDataKey: String = "filtered_data"

    @Inject
    internal lateinit var dataPresenter: DataPresenterImpl

    private lateinit var rootView: View

    private lateinit var searchV: EditText

    private var dialogue: AlertDialog? = null

    private var fullData: BreedDataRaw? = null

    private var filteredData: BreedDataRaw? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            (activity as MainActivity).initializeInjector()

            filteredData = savedInstanceState.getParcelable(filteredDataKey)

            fullData = savedInstanceState.getParcelable(downloadedDataKey)
        }

        this.getComponent(DataComponent::class.java).inject(this)

        dataPresenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        needToGetCatFact()

        rootView = inflater.inflate(R.layout.fragment_data, null)

        searchV = rootView.findViewById(R.id.search_edTxt_fragData)

        if (filteredData == null) {
            if (fullData != null) {
                filteredData = fullData

                filterRestoredData(passDataToView(rootView, filteredData!!.data as ArrayList<BreedDataRaw.Data>))
            }
            else
                needToGetBreedsList()
        }
        else {
            filterRestoredData(passDataToView(rootView, filteredData!!.data as ArrayList<BreedDataRaw.Data>))
        }

        return rootView
    }

    override fun needToGetCatFact() {
        dataPresenter.handleGettingCatFact()
    }

    override fun onGotCatFact(text: String) {
        createFactDialogue(text)
    }

    private fun createFactDialogue(text: String) {
        dialogue = AlertDialog.Builder(activity as Context)
            ///.setView(R.layout.dialogue_fact)
            .create()

        dialogue!!.setOnShowListener {
            val window = dialogue!!.window

            window!!.setBackgroundDrawableResource(android.R.color.transparent)

            window.setContentView(R.layout.dialogue_fact)

            dialogue!!.findViewById<TextView>(R.id.fact_txtV_dialFact)!!.text = text

            val btnCool = dialogue!!.findViewById<FrameLayout>(R.id.coolCont_framLay_dialFact)

            btnCool!!.setOnClickListener {
                dialogue!!.dismiss()
            }
        }

        dialogue!!.show()
    }

    override fun onCatFactError(errorMessage: String) {
        Toast.makeText(activity, "Getting cat fact error: $errorMessage", Toast.LENGTH_SHORT).show()
    }

    override fun needToGetBreedsList() {
        dataPresenter.handleGettingBreedsList()
    }

    override fun onGotBreedsList(rawDat: BreedDataRaw) {
        val list: ArrayList<BreedDataRaw.Data> = rawDat.data as ArrayList<BreedDataRaw.Data>

        val breedsAdapter = passDataToView(rootView, list)

        searchV.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val filteredList = ArrayList<BreedDataRaw.Data>()

                for (i in list.indices) {
                    if (list[i]._breed.contains(s, true))
                        filteredList.add(list[i])
                }

                breedsAdapter.setList(filteredList)

                val filteredRawD = BreedDataRaw()

                filteredRawD.data = filteredList

                filteredData = filteredRawD
            }
        })

        fullData = rawDat

        Toast.makeText(activity, "Downloded.", Toast.LENGTH_SHORT).show()
    }

    private fun passDataToView(rootView: View, list: ArrayList<BreedDataRaw.Data>): BreedsAdapter {
        val breedsAdapter = BreedsAdapter(list, activity as Context)

        val recView = rootView.findViewById<RecyclerView>(R.id.data_recV_fragData)

        recView.adapter = breedsAdapter

        recView.layoutManager = LinearLayoutManager(activity)

        return breedsAdapter
    }

    private fun filterRestoredData (adapter: BreedsAdapter) {
        searchV.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                val filteredList = ArrayList<BreedDataRaw.Data>()

                for (i in fullData!!.data!!.indices) {
                    if (fullData!!.data!![i]._breed.contains(p0, true))
                        filteredList.add(fullData!!.data!![i])
                }

                adapter.setList(filteredList)

                val filteredRawD = BreedDataRaw()

                filteredRawD.data = filteredList

                filteredData = filteredRawD
            }

        })

        Toast.makeText(activity, "Restored.", Toast.LENGTH_SHORT).show()
    }

    override fun onBreedsListError(errorMessage: String) {
        Toast.makeText(activity, "Getting breeds list error: $errorMessage", Toast.LENGTH_SHORT).show()
    }

    override fun onBackEndError(errorMessage: String) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (filteredData != null)
            outState.putParcelable(filteredDataKey, filteredData)

        if (fullData != null)
            outState.putParcelable(downloadedDataKey, fullData)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (dialogue != null) {
            if (dialogue!!.isShowing) {
                dialogue!!.dismiss()

                dialogue = null
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        dataPresenter.cleanView()
    }
}