package com.tugrulbo.hygge.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.tugrulbo.hygge.R

class ProgressDialog(context : Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
    }
}