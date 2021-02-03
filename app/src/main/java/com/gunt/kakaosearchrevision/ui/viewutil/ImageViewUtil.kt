package com.gunt.kakaosearchrevision.ui.viewutil

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gunt.kakaosearchrevision.R

object ImageViewExtensions {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(thumbnailImgView: ImageView, imgUrl: String?) {
        Glide.with(thumbnailImgView)
            .load(imgUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground) // Error Image 있을 경우 변경
            .into(thumbnailImgView)
    }
}
