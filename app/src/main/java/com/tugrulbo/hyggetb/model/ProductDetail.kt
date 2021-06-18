package com.tugrulbo.hyggetb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetail(
    @SerializedName("brandName")
    var brandName: String?,
    @SerializedName("category")
    var category: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("images")
    var images: List<String>,
    @SerializedName("latitude")
    var latitude: String,
    @SerializedName("longitude")
    var longitude: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("priceDiscount")
    var priceDiscount: String,
    @SerializedName("productDetailInfo")
    var productDetailInfo: String,
    @SerializedName("productName")
    var productName: String,
    @SerializedName("sizes")
    var sizes: List<String>
):Parcelable