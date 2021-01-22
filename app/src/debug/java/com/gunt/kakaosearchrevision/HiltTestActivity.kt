package com.gunt.kakaosearchrevision

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gunt.kakaosearchrevision.navigator.Screens
import com.gunt.kakaosearchrevision.ui.Communicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity(),Communicator {
    override fun passData(screens: Screens, argument: Bundle) {

    }
}
