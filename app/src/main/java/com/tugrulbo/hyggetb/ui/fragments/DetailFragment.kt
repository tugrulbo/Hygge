package com.tugrulbo.hyggetb.ui.fragments

import android.app.Dialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tugrulbo.hyggetb.R
import com.tugrulbo.hyggetb.model.ProductDetail
import com.tugrulbo.hyggetb.network.NetworkHelper
import com.tugrulbo.hyggetb.ui.fragments.adapters.DetailImageSliderAdapter
import com.tugrulbo.hyggetb.ui.fragments.adapters.SizesAdapter
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


class DetailFragment : Fragment() {

    private val networkHelper = NetworkHelper()
    private var id:String?=null
    private var productDetail:ProductDetail?=null
    private var count =1
    private var comm :Communicator?=null

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
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                holder()
                            }
                            onClickEvents()
                            imageSliderImplementation(context!!,productDetail!!.images)
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
    private fun imageSliderImplementation(context: Context,sizes:List<String>){
        val adapter = DetailImageSliderAdapter(context,sizes)
        vpDetailImage.adapter = adapter
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

        productDetail?.sizes?.let {
               val count =(productDetail?.sizes!!.size)/3
            var counter = 0
            var range=2 + counter
               for (j in 1..count){
                   val row = LinearLayout(context)
                   row.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                   for (i in counter..range){
                       var btn = Button(context)
                       btn.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                       btn.text = productDetail?.sizes!![i]
                       row.addView(btn)
                       counter++
                   }

                   linearLDetailSizes.addView(row)
               }

        }

    }

    /*private fun getProductSizes(){
            rvDetailSizes.layoutManager = GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL,false)
            rvDetailSizes.adapter=SizesAdapter(productDetail!!.sizes)
    }
*/

    private fun onClickEvents(){
        ivCountBack.setOnClickListener {
            if (count>=1){
                tvCount.text = count.toString()
                count--
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


        }

        ivBack.setOnClickListener {
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, HomeFragment()).commit()

        }
    }

    private fun showCustomDialog() {
        CartCustomDialog(object : OnClickInterface {
            override fun onClickConfirm(dialog: Dialog) {
                var sharedPreferences = requireActivity().getSharedPreferences(Constants.cartInfo,Context.MODE_PRIVATE)
                var editor = sharedPreferences.edit()
                editor.putString("product",productDetail?.id.toString())
                editor.putString(Constants.cartIsEmpty,"false")
                editor.commit()
                val fragmentTransaction = requireFragmentManager().beginTransaction()
                fragmentTransaction.replace(R.id.frameLayout, HomeFragment()).commit()
            }

        }).show(parentFragmentManager,"Custom Dialog")
    }

}