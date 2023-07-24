package com.credopay

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(url:String){
    this.load(url)
}