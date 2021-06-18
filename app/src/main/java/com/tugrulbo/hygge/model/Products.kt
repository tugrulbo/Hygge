package com.tugrulbo.hygge.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(
    @SerializedName("categoryList")
    var categoryList: List<Category>?,
    @SerializedName("mainTitle")
    var mainTitle: String?,
    @SerializedName("productList")
    var productList: List<Product>?,
    @SerializedName("subTitle")
    var subTitle: String?
):Parcelable