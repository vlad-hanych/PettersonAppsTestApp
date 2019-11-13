package com.aspire.pettersonappstestapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aspire.pettersonappstestapp.R
import com.aspire.pettersonappstestapp.presentation.view.MainActivity
import com.aspire.pettersonappstestapp.substances.BreedDataRaw
import kotlinx.android.synthetic.main.item_breed.view.*

class BreedsAdapter(private var dataList: ArrayList<BreedDataRaw.Data>, private val context: Context) :
    RecyclerView.Adapter<BreedsViewHolder>() {
    private val countryTemplate: String = "Country: "

    private val originTemplate: String = "Origin: "

    private val coatTemplate: String = "Coat: "

    private val patternTemplate: String = "Pattern: "
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BreedsViewHolder {
        return BreedsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_breed, parent, false))
    }

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        holder.name.text = dataList[position]._breed

        holder.country.text = countryTemplate + dataList[position]._country

        holder.origin.text = originTemplate + dataList[position]._origin

        holder.coat.text = coatTemplate + dataList[position]._coat

        holder.pattern.text = patternTemplate + dataList[position]._pattern

        holder.share.setOnClickListener {
            (context as MainActivity).share(dataList[position]._breed,
                                            dataList[position]._country,
                                            dataList[position]._origin,
                                            dataList[position]._coat,
                                            dataList[position]._pattern)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList (anotherList: ArrayList<BreedDataRaw.Data>) {
        dataList = anotherList

        notifyDataSetChanged()
    }
}

class BreedsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.name_txtV_itemBr

    var share: Button = view.share_butt_itemBr

    var country: TextView = view.country_txtV_itemBr

    var origin: TextView = view.origin_txtV_itemBr

    var coat: TextView = view.coat_txtV_itemBr

    var pattern: TextView = view.pattern_txtV_itemBr
}