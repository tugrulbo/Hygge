package com.tugrulbo.hyggetb.ui.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugrulbo.hyggetb.R
import com.tugrulbo.hyggetb.model.Product
import com.tugrulbo.hyggetb.model.Products
import com.tugrulbo.hyggetb.network.NetworkHelper
import com.tugrulbo.hyggetb.ui.fragments.adapters.ImageSliderAdapter
import com.tugrulbo.hyggetb.ui.fragments.adapters.ProductRecyclerViewAdapter
import com.tugrulbo.hyggetb.ui.fragments.adapters.ProductRecyclerViewAdapter.*
import com.tugrulbo.hyggetb.util.Communicator
import com.tugrulbo.hyggetb.util.Helper
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(), OnListClickListener {

    private val networkHelper = NetworkHelper()
    private var productList:Products?=null
    private var productListAdapter:ProductRecyclerViewAdapter?=null
    val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context,2)
    private var helper:Helper?=null
    private lateinit var comm: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAllData()

        helper?.checkCart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun getAllData(){
        try {
            Log.d(TAG, "getAllData: $networkHelper")
            networkHelper.productsData?.getAllData()?.enqueue(object :Callback<Products>{
                override fun onResponse(call: Call<Products>, response: Response<Products>) {
                    Log.d(TAG, "getAllData: ${response.body()}")
                   if(response.isSuccessful)
                   {
                       response.body()?.let {
                           productList = it
                       }

                       holder()
                       itemRecyclerViewImplementation()
                       imageSliderImplementation()
                   }

                   }
                override fun onFailure(call: Call<Products>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.localizedMessage}")
                }

            })
        }catch (e:Exception){
            e.printStackTrace()
        }

    }


    private fun imageSliderImplementation(){
        val adapter = ImageSliderAdapter(context,productList?.categoryList)
        vpSlider.adapter = adapter
    }

    private fun itemRecyclerViewImplementation(){
        productListAdapter = ProductRecyclerViewAdapter(productList?.productList,this)
        rvItems?.layoutManager=layoutManager
        rvItems.adapter=productListAdapter
        productListAdapter!!.notifyDataSetChanged()
    }

    override fun onClick(productList: List<Product>?, position: Int) {
        Log.e(TAG, "onClick: ${productList!![position]}")
        comm = requireActivity() as Communicator
        comm.passDataCom(productList[position].id.toString())


    }

    private fun holder(){
        tvSliderTitle.text = productList?.mainTitle
        tvItemsTitle.text = productList?.subTitle
    }


}