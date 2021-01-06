package com.gunt.kakaosearchrevision.ui

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.data.BookDTO
import com.gunt.kakaosearchrevision.databinding.FragmentBookDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookDetailFragment : Fragment() {

    lateinit var binding: FragmentBookDetailBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_book_detail, container, false)
        val view = binding.root
        val bookDTO: BookDTO? = arguments?.getSerializable("book") as BookDTO
        binding.bookDetail = bookDTO
        binding.tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        binding.imgClose.setOnClickListener { this.activity?.supportFragmentManager?.popBackStack() }

        return view
    }

}