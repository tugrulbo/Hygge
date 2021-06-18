package com.tugrulbo.hyggetb.ui.fragments

import android.app.Dialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.airbnb.lottie.animation.content.Content
import com.squareup.picasso.Picasso
import com.tugrulbo.hyggetb.R
import com.tugrulbo.hyggetb.model.ProductDetail
import com.tugrulbo.hyggetb.network.NetworkHelper
import com.tugrulbo.hyggetb.ui.fragments.adapters.DetailImageSliderAdapter
import com.tugrulbo.hyggetb.ui.fragments.adapters.ImageSliderAdapter
import com.tugrulbo.hyggetb.util.Communicator
import com.tugrulbo.hyggetb.util.Constants
import com.tugrulbo.hyggetb.util.Helper
import com.tugrulbo.hyggetb.view.CartCustomDialog
import com.tugrulbo.hyggetb.view.OnClickInterface
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.ivBack
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_map.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.system.exitProcess


class DetailFragment : Fragment() {

    private val networkHelper = NetworkHelper()
    private var id:String?=null
    private var productDetail:ProductDetail?=null
    private var count =1
    private var comm :Communicator?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        id = arguments?.getString("id")
        Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
        getDetails()
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private fun getDetails(){
       try {
           networkHelper.productsData?.getDetailData(id!!)?.enqueue(object : Callback<ProductDetail> {
               override fun onResponse(call: Call<ProductDetail>, response: Response<ProductDetail>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            productDetail = it
                            holder()
                            onClickEvents()
                            //imageSliderImplementation()
                        }
                        Log.d(TAG, "onResponse: $productDetail")

                    }
               }
               override fun onFailure(call: Call<ProductDetail>, t: Throwable) {
                   Log.d(ContentValues.TAG, "onResponse: ${t.localizedMessage}")
               }

           })
       }catch (e:Exception){
            e.printStackTrace()
       }
    }
    private fun imageSliderImplementation(){
        val adapter = DetailImageSliderAdapter(context,productDetail)
        vpSlider.adapter = adapter
    }

    private fun holder(){

        productDetail?.productName?.let {
            txProductTitle.text = it
        }?: run {
            txProductTitle.text = R.string.not_found.toString()
        }

        productDetail?.price?.let {
            tvPrice.text = it
        }?:run {
           tvPrice.text=R.string.not_found.toString()
        }

        productDetail?.priceDiscount?.let {
            tvPriceDiscount.text = it
        }?:run {
            tvPriceDiscount.visibility = View.GONE
        }

        productDetail?.productDetailInfo?.let {
            tvProductDesc.text = it
        }?:run {
           tvProductDesc.text = R.string.not_found.toString()
        }

    }

    private fun onClickEvents(){
        ivCountBack.setOnClickListener {
            if (count>=1){
                count--
                tvCount.text = count.toString()
            }

        }
        ivCountForward.setOnClickListener {
           if (count<=10){
               tvCount.text = count.toString()
               count++
           }
        }

        btnStoreLocation.setOnClickListener {
            comm = requireActivity() as Communicator
            comm?.passMapData(productDetail?.brandName,productDetail?.latitude,productDetail?.longitude)
        }

        btnCart.setOnClickListener {
                showCustomDialog()
            ivShoppingCartDetail.setImageResource(R.drawable.ic_shopping_cart_filled)
            var sharedPreferences = context?.getSharedPreferences(Constants.cartInfo,Context.MODE_PRIVATE)
            var editor = sharedPreferences?.edit()
            editor?.putString("product",productDetail?.id)
            editor?.putBoolean("isEmpty",false)
            editor?.commit()

        }

        ivBack.setOnClickListener {
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, HomeFragment()).commit()

        }
    }

    private fun showCustomDialog() {
        CartCustomDialog(object : OnClickInterface {
            override fun onClickConfirm(dialog: Dialog) {
                val fragmentTransaction = requireFragmentManager().beginTransaction()
                fragmentTransaction.replace(R.id.detailFrameLayout, HomeFragment()).commit()
            }

        }).show(parentFragmentManager,"Custom Dialog")
    }

}