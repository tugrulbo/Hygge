package com.tugrulbo.hyggetb.util

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.tugrulbo.hyggetb.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.max

class Helper: Activity() {



    fun checkCart(){
        val sharedPreferences = getSharedPreferences(Constants.cartInfo,Context.MODE_PRIVATE)
        val isEmpty = sharedPreferences.getString(Constants.cartIsEmpty,"")
        if(isEmpty=="false"){
            ivShoppingCart.setImageResource(R.drawable.ic_shopping_cart_filled)
        }else
        {
            ivShoppingCart.setImageResource(R.drawable.ic_shopping_cart)
        }

    }

    fun getSafeString(s: String, maxLength:Int): String {
        if(s.isNotEmpty()){
            if(s.length > maxLength ){
                return s.substring(0,maxLength)+"..."
            }
        }
        return s
    }

}
