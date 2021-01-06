package com.gunt.kakaosearchrevision.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunt.kakaosearchrevision.data.BookDTO
import com.gunt.kakaosearchrevision.data.ResultBook
import com.gunt.kakaosearchrevision.databinding.ItemBookBinding


class BooksListAdapter : RecyclerView.Adapter<BooksListAdapter.BookViewHolder>() {
    var items = ArrayList<BookDTO>()
    private var recyclerViewClickListener: OnRecyclerViewClickListener<BookDTO>? = null

    fun setDataList(data: ResultBook?) {
        this.items = data?.documents ?: ArrayList()
        notifyDataSetChanged()
    }

    fun setRecyclerClickListener(recyclerClickListener: OnRecyclerViewClickListener<BookDTO>) {
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
        holder.itemView.setOnClickListener { recyclerViewClickListener?.onRecyclerViewClickListener(items[position]) }
    }

    class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BookDTO) {
            binding.tempBook = data
            binding.executePendingBindings()
        }
    }
}