package com.gunt.kakaosearchrevision.ui.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gunt.kakaosearchrevision.R

object Util{
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(thumbnailImgView: ImageView, imgUrl: String?) {
        Glide.with(thumbnailImgView)
            .load(imgUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(thumbnailImgView)
    }

    @JvmStatic
    @BindingAdapter("setPriceStr")
    fun setPriceStr(textView: TextView, price: Long) {
        textView.text = "$price 원"
    }
}