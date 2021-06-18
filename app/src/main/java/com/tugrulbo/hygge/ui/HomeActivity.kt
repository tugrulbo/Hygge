package com.tugrulbo.hygge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tugrulbo.hygge.R
import com.tugrulbo.hygge.model.Products
import com.tugrulbo.hygge.network.NetworkHelper
import com.tugrulbo.hygge.ui.adapters.ImageSliderAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : AppCompatActivity() {
    private val networkHelper:NetworkHelper?=null
    private val productList:MutableList<Products>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_fragment)
        getAllData()
        imageSliderImplementation()
    }

    private fun getAllData(){
        networkHelper?.productsData?.getAllData()?.enqueue(object :Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
               if (response.isSuccessful){
                   response.body()?.let {
                       productList?.add(it)
                   }
               }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun imageSliderImplementation() {
        val adapter = ImageSliderAdapter(this.applicationContext,productList!![0].categoryList)
        vpSlider.adapter = adapter
    }



}