package com.tugrulbo.hyggetb.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tugrulbo.hyggetb.R
import kotlinx.android.synthetic.main.custom_dialog.*

class CartCustomDialog(private var onClickInterface:OnClickInterface):DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.shape_rounded_item)
        return inflater.inflate(R.layout.custom_dialog, container, false)

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        btnPopupRedirection.setOnClickListener {
            onClickInterface.onClickConfirm(dialog!!)
        }

    }
}

interface OnClickInterface {
    fun onClickConfirm(dialog: Dialog)

}