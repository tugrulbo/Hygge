package com.tugrulbo.hyggetb.ui.fragments.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.tugrulbo.hyggetb.R
import com.tugrulbo.hyggetb.model.Category
import com.tugrulbo.hyggetb.model.Product
import com.tugrulbo.hyggetb.model.ProductDetail
import com.tugrulbo.hyggetb.model.Products
import com.tugrulbo.hyggetb.ui.fragments.DetailFragment
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_viewpager.view.*
import java.util.*

class DetailImageSliderAdapter(private val context: Context?, private var sliders: List<String>) : PagerAdapter() {

    private var inflater: LayoutInflater? = null
    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`
    }

    override fun getCount(): Int {

        return sliders!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.item_viewpager, null)
        var imageUrl = sliders!![position]
        Log.d(TAG, "instantiateItem: ${sliders!![position]}")
        Picasso.get().load(imageUrl).resize(600,800).into(view.iVSliderImage)

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}