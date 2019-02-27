package com.exercise.todolist.ui

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.exercise.todolist.viewmodel.ItemViewModel
import com.exercise.todolist.R
import com.exercise.todolist.databinding.TodoItemBinding
import com.exercise.todolist.model.Item

class ItemListAdapter internal constructor(
    context: AppCompatActivity,
    private val itemViewModel: ItemViewModel
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {
    private var items = emptyList<Item>() // Cached copy of items

    init {
        itemViewModel.allItems.observe(context, Observer { repoItems ->
            // Update the cached copy of the words in the adapter.
            repoItems?.let { repoItems ->
                this.items = repoItems
                notifyDataSetChanged()
            }
        })
    }

    override fun onCreateViewHolder(recyclerView: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(recyclerView.context)
        val binding = TodoItemBinding.inflate(inflater, recyclerView, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ItemViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(current : Item) {
            binding.apply {
                viewModel = itemViewModel
                item = current
            }
        }
    }

}

