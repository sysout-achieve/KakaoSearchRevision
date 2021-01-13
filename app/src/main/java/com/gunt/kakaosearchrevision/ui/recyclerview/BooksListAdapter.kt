package com.gunt.kakaosearchrevision.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunt.kakaosearchrevision.repository.api.model.BookDto
import com.gunt.kakaosearchrevision.repository.api.response.ResponseBook
import com.gunt.kakaosearchrevision.databinding.ItemBookBinding
import com.gunt.kakaosearchrevision.domain.data.Book
import kotlin.collections.ArrayList

class BooksListAdapter : RecyclerView.Adapter<BooksListAdapter.BookViewHolder>() {
    private var items = ArrayList<Book>()
    private var recyclerViewClickListener: OnRecyclerViewClickListener<Book>? = null

    fun setDataList(data: List<Book>) {
        this.items.addAll(data)
        notifyDataSetChanged()
    }


    fun setRecyclerClickListener(recyclerClickListener: OnRecyclerViewClickListener<Book>) {
        recyclerViewClickListener = recyclerClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBookBinding.inflate(layoutInflater)
        return BookViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            recyclerViewClickListener?.onRecyclerViewClickListener(items[position])
        }
    }

    fun clearData() {
        items.clear()
    }

    class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Book) {
            binding.tempBook = data
            binding.executePendingBindings()
        }
    }
}