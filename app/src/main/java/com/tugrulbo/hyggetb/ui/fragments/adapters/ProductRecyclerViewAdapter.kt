package com.tugrulbo.hyggetb.ui.fragments.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tugrulbo.hyggetb.R
import com.tugrulbo.hyggetb.model.Product
import com.tugrulbo.hyggetb.util.Helper
import kotlinx.android.synthetic.main.category_title.view.*
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class ProductRecyclerViewAdapter(private val productList:List<Product>?, var onListClickListener: OnListClickListener):
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val helper = Helper()

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(){
            val item = productList!![adapterPosition]
            Picasso.get().load(item.image).resize(500,800).into(itemView.ivProductImg)

            item.productName?.let {
                itemView.tvProductTitle.text = helper.getSafeString(item.productName.toString(),25)
            }?: run {
                itemView.tvProductTitle.text = R.string.not_found.toString()
            }

            item.category?.let {
                when(item.category){
                    "FEMALE" -> {
                        itemView.tvCategory.setBackgroundResource(R.color.category_pink_bg)
                        itemView.tvCategory.setTextColor(ContextCompat.getColor(context,R.color.category_pink_title))
                        itemView.tvCategory.text = item.category
                    }
                    "MALE" -> {
                        itemView.tvCategory.setBackgroundResource(R.color.category_blue_bg)
                        itemView.tvCategory.setTextColor(ContextCompat.getColor(context,R.color.category_blue_title))
                        itemView.tvCategory.text = item.category
                    }

                }
            }?: run {
                itemView.tvCategory.text = R.string.not_found.toString()
            }

            item.price?.let {
                itemView.tvPrice.paintFlags = itemView.tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                itemView.tvPrice.text = item.price
            }?: run {
                itemView.tvPrice.text = item.priceDiscount
                itemView.tvPrice.visibility=View.GONE
            }

           item.priceDiscount?.let {
               itemView.tvPriceDiscount.text = item.priceDiscount
           }?: run {
               itemView.tvPrice.visibility = View.GONE

           }

            itemView.setOnClickListener {
                onListClickListener.onClick(productList,adapterPosition)
            }

        }
    }
    interface OnListClickListener {
        fun onClick(productList: List<Product>?,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
       return productList!!.size
    }

    /*private fun customCategory(itemView: View,productList: List<Product>?,position: Int){
        itemView.tvCategory.setBackgroundResource(R.color.category_pink_bg)
        itemView.tvCategory.setTextColor(ContextCompat.getColor(context,R.color.category_pink_title))
        itemView.tvCategory.text = productList!![position].category
    }*/
}