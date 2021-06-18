package com.tugrulbo.hygge.network

import com.tugrulbo.hygge.model.ProductDetail
import com.tugrulbo.hygge.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetProductsData {

    @GET("main_page.php")
    fun getAllData(): Call<Products>

    @GET("product_detail.php?productId={productId}")
    fun getDetailData(@Query("productId") productId: String): Call<ProductDetail>
}