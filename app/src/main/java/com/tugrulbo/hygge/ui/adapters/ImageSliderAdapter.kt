package com.tugrulbo.hygge.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.tugrulbo.hygge.R
import com.tugrulbo.hygge.model.Category
import kotlinx.android.synthetic.main.item_viewpager.view.*

class ImageSliderAdapter(private val context:Context, private var imageSlides:List<Category>?) : PagerAdapter() {

    private var inflater:LayoutInflater? = null



    override fun getCount(): Int {
        return imageSlides!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view===`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.item_viewpager, null)
        var imageUrl = imageSlides!![position].imageUrl
        Picasso.get().load(imageUrl).into(view.iVSliderImage)

        view.setOnClickListener(View.OnClickListener {
            var gameId = imageSlides!![position].id
            Toast.makeText(context, "$gameId", Toast.LENGTH_SHORT).show()

            /*val intent = Intent(context!!,::class.java)
            intent.putExtra("gameId",gameId)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(context,intent,null)*/
        })

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