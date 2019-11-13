package com.aspire.pettersonappstestapp.substances

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreedDataRaw (
@SerializedName("data")
@Expose
var data : List<Data>? = null) : Parcelable {

    @Parcelize
    class Data (@SerializedName("breed") val _breed: String,
                       @SerializedName("country") val _country: String,
                       @SerializedName("origin") val _origin: String,
                       @SerializedName("coat") val _coat: String,
                       @SerializedName("pattern") val _pattern: String) : Parcelable
}