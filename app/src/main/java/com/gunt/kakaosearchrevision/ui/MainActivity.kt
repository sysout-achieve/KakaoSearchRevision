package com.gunt.kakaosearchrevision.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gunt.kakaosearchrevision.GlobalExceptionHandler
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.databinding.ActivityMainBinding
import com.gunt.kakaosearchrevision.navigator.AppNavigator
import com.gunt.kakaosearchrevision.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Communicator {

    @Inject
    lateinit var navigator: AppNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)
        GlobalExceptionHandler.setActivity(this)
        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.List)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }

    override fun passData(argument: Bundle) {
        navigator.navigateTo(Screens.Detail, argument)
    }
}
