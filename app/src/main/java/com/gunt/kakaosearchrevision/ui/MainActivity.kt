package com.gunt.kakaosearchrevision.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gunt.kakaosearchrevision.GlobalExceptionHandler
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)
        GlobalExceptionHandler.setActivity(this)
    }
}
