package com.tugrulbo.hyggetb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("brandName")
    var brandName: String?,
    @SerializedName("category")
    var category: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("price")
    var price: String?,
    @SerializedName("priceDiscount")
    var priceDiscount: String?,
    @SerializedName("productName")
    var productName: String?
):Parcelable