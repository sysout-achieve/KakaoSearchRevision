package com.gunt.kakaosearchrevision.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.databinding.ActivityErrorBinding

class ErrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorBinding

    private val errorText by lazy { intent.getStringExtra(EXTRA_ERROR_TEXT) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_error)

        binding.txtErrorContents.text = errorText

        binding.button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        const val EXTRA_ERROR_TEXT = "EXTRA_ERROR_TEXT"
    }
}