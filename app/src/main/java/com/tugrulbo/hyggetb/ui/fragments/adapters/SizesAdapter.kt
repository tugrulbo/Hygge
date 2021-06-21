package com.tugrulbo.hyggetb.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugrulbo.hyggetb.R

class SizesAdapter(private var sizes:List<String>, var onSizeClickListener:OnSizeClickListener, var selected:Int):RecyclerView.Adapter<SizesAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvSize: TextView = view.findViewById(R.id.tvDetailSizes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_sizepicker, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizesAdapter.ViewHolder, position: Int) {
        holder.tvSize.text = sizes[position]
        if (position == selected) {
            //TODO diğer itemleri false yap
        }
        holder.itemView.setOnClickListener {
            onSizeClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return sizes.size
    }

    interface OnSizeClickListener{
        fun onClick(position: Int)
    }

}