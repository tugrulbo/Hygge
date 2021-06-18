package com.tugrulbo.hyggetb.network

import com.tugrulbo.hyggetb.model.ProductDetail
import com.tugrulbo.hyggetb.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetProductsData {

    @GET("main_page.php")
    fun getAllData(): Call<Products>

    @GET("product_detail.php")
    fun getDetailData(@Query("productId") id: String): Call<ProductDetail>
}