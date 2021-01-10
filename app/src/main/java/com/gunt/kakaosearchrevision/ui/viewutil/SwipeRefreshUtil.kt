package com.gunt.kakaosearchrevision.ui.viewutil

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gunt.kakaosearchrevision.R

enum class SwipeRefreshTheme(val indicatorColor: Int) {
    MAIN(R.color.colorPrimary),
    DARK(R.color.black)
}

fun SwipeRefreshLayout.setIndicatorColor(theme: SwipeRefreshTheme) {
    setColorSchemeColors(theme.indicatorColor)
}
