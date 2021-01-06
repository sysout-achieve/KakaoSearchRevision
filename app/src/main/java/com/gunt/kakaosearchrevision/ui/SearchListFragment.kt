package com.gunt.kakaosearchrevision.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.gunt.kakaosearchrevision.BR
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.data.BookDTO
import com.gunt.kakaosearchrevision.databinding.FragmentSearchListBinding
import com.gunt.kakaosearchrevision.ui.recyclerview.EndlessRecyclerOnScrollListener
import com.gunt.kakaosearchrevision.ui.recyclerview.OnRecyclerViewClickListener
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

const val REQUEST_ENDLESS_CNT: Int = 10

@AndroidEntryPoint
class SearchListFragment : Fragment() {

    private lateinit var binding: FragmentSearchListBinding

    private val viewModel: SearchListViewModel by viewModels()

    private lateinit var communicator: Communicator

    private var compositeDisposable = CompositeDisposable()

    private var recyclerViewClickListener = object : OnRecyclerViewClickListener<BookDTO> {
        override fun onRecyclerViewClickListener(item: BookDTO) {
            val bundle = Bundle()
            bundle.putSerializable("book", item)
            communicator.passData(bundle)
        }
    }

    private var recyclerViewEndScrollListener = object : EndlessRecyclerOnScrollListener(REQUEST_ENDLESS_CNT) {
        override fun onLoadMore() {
            binding.viewModel?.callMoreSearchBooksList()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        communicator = activity as Communicator

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search_list, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        setupSearchEditTextChangeListener()
        setupBinding()
        setupEndScrollListener()
        setupRecyclerViewItemClickListener()
        val view = binding.root

        binding.lifecycleOwner = this

        viewModel.getBooksDataObserver().observe(this.viewLifecycleOwner, Observer {
            viewModel.setBooksDataAdapter(it)
        })

        binding.btnSearch.setOnClickListener {
            viewModel.callSearchBooksList()
        }

        return view
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    private fun setupSearchEditTextChangeListener() {
        val subscription: Disposable =
                binding.editSearch.textChanges().debounce(200, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    viewModel.callSearchBooksList()
                                    Log.d("RX", "onNext $it")
                                },
                                onComplete = {
                                    Log.d("RX", "onComplete")
                                },
                                onError = {
                                    Log.d("RX", "onError : $it")
                                }
                        )
        compositeDisposable.add(subscription)
    }

    private fun setupBinding() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchListFragment.context)
            val decoration = DividerItemDecoration(this@SearchListFragment.context, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun setupRecyclerViewItemClickListener() {
        viewModel.booksListAdapter.setRecyclerClickListener(recyclerViewClickListener)
    }

    private fun setupEndScrollListener() {
        binding.recyclerView.addOnScrollListener(recyclerViewEndScrollListener)
    }
}
