package com.gunt.kakaosearchrevision.ui.viewutil

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.gunt.kakaosearchrevision.R

var PRICE_UNIT = "원"

object TextViewExtensions {

    @JvmStatic
    @BindingAdapter("bindTextViewWithPriceStr")
    fun bindTextViewWithPriceStr(textView: TextView, price: Long) {
        textView.text = getPriceStrWithCurrentUnit(price)
    }
    fun getPriceStrWithCurrentUnit(price: Long): String {
        return "$price $PRICE_UNIT"
    }
}

enum class TextViewTheme(val fontSize: Float, val textColor: Int) {
    CLASSIC(20f, R.color.black),
    TITLE(34f, R.color.black),
    CONTENTS(14f, R.color.black)
}

fun TextView.setTextTheme(theme: TextViewTheme) {
    this.textSize = theme.fontSize
    setTextColor(theme.textColor)
}

fun TextView.scratchText() {
    this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}
