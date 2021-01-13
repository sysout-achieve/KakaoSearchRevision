package com.gunt.kakaosearchrevision.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.databinding.FragmentBookDetailBinding
import com.gunt.kakaosearchrevision.domain.data.Book
import com.gunt.kakaosearchrevision.ui.viewutil.scratchText
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
        val book: Book? = arguments?.getSerializable("book") as Book
        binding.bookDetail = book

        binding.tvPrice.scratchText()

        binding.imgClose.setOnClickListener { this.activity?.onBackPressed() }

        return view
    }

}