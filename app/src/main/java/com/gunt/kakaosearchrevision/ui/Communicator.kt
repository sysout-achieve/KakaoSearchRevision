package com.gunt.kakaosearchrevision.ui

import android.os.Bundle
import com.gunt.kakaosearchrevision.navigator.Screens

interface Communicator {
    fun passData(screens: Screens,argument : Bundle)
}