package com.gunt.kakaosearchrevision.navigator

import android.os.Bundle

enum class Screens {
    Detail,
    List
}

interface AppNavigator {
    fun navigateTo(screens: Screens)
    fun navigateTo(screens: Screens, argument:Bundle)
}