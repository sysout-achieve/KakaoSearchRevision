package com.gunt.kakaosearchrevision.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gunt.kakaosearchrevision.BR
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.databinding.FragmentBookDetailBinding
import com.gunt.kakaosearchrevision.ui.viewutil.PRICE_UNIT
import com.gunt.kakaosearchrevision.ui.viewutil.scratchText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment() {

    lateinit var binding: FragmentBookDetailBinding

    private val viewModel: BookDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_book_detail, container, false)

        binding.lifecycleOwner = this

        PRICE_UNIT = getString(R.string.price_unit)
        val view = binding.root

        val args: BookDetailFragmentArgs by navArgs()
        binding.setVariable(BR.detailViewModel, viewModel)
        viewModel.book = args.book

        binding.tvPrice.scratchText()

        binding.imgClose.setOnClickListener { this.activity?.onBackPressed() }

        return view
    }
}
