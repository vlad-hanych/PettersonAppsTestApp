package com.aspire.pettersonappstestapp.substances

import com.google.gson.annotations.SerializedName

data class CatFactDataRaw(@SerializedName("fact") private val _fact: String,
                          @SerializedName("length") private val _length: Int) {
    val fact: String
        get() = _fact

    val length: Int
        get() = _length
}