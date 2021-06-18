package com.tugrulbo.hyggetb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("campaignName")
    var campaignName: String?,
    @SerializedName("category")
    var category: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("imageUrl")
    var imageUrl: String?
):Parcelable