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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.gunt.kakaosearchrevision.BR
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.databinding.FragmentSearchListBinding
import com.gunt.kakaosearchrevision.ui.recyclerview.EndlessRecyclerOnScrollListener
import com.gunt.kakaosearchrevision.ui.recyclerview.OnRecyclerViewClickListener
import com.gunt.kakaosearchrevision.ui.viewutil.SwipeRefreshTheme
import com.gunt.kakaosearchrevision.ui.viewutil.setIndicatorColor
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

    //구독하고 있는 Disposable 객체 일괄 관리용 객체(리소스 일괄 제어)
    private var compositeDisposable = CompositeDisposable()

    private var recyclerViewClickListener =
            object : OnRecyclerViewClickListener<Book> {
                override fun onRecyclerViewClickListener(item: Book) {
                    val action = SearchListFragmentDirections.listToDetail(item)
                    findNavController().navigate(action)
                }
            }

    private var recyclerViewEndScrollListener =
            object : EndlessRecyclerOnScrollListener(REQUEST_ENDLESS_CNT) {
                override fun onLoadMore() {
                    binding.viewModel?.onTriggerEvent(BookListEvent.NextPageEvent)
                }
            }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search_list, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        val view = binding.root
        setupBinding()
        setupEndScrollListener()
        setupRecyclerViewItemClickListener()
        setupSwipeRefresh()
        setupSearchEditTextChangeListener()

        binding.lifecycleOwner = this

        viewModel.getLoading().observe(this.viewLifecycleOwner, Observer {
            binding.layoutSwipeRefresh.isRefreshing = it
        })

        binding.btnSearch.setOnClickListener {
            viewModel.onTriggerEvent(BookListEvent.NewSearchEvent)
        }

        return view
    }

    override fun onDestroyView() {
        //화면 종료 시 Disposable 탐색 해제
        compositeDisposable.clear()
        super.onDestroyView()
    }

    //자동 검색 기능 추가, 버튼 누르지 않고 검색
    private fun setupSearchEditTextChangeListener() {
        val subscription: Disposable =
                binding.editSearch.textChanges().debounce(200, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    viewModel.onTriggerEvent(BookListEvent.NewSearchEvent)
                                    Log.d("kakao RX", "onNext $it")
                                },
                                onComplete = {
                                    Log.d("kakao RX", "onComplete")
                                },
                                onError = {
                                    Log.d("kakao RX", "onError : $it")
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

    private fun setupSwipeRefresh() {
        binding.layoutSwipeRefresh.setIndicatorColor(SwipeRefreshTheme.MAIN)
        binding.layoutSwipeRefresh.setOnRefreshListener {
            viewModel.onTriggerEvent(BookListEvent.NewSearchEvent)
        }
    }
}