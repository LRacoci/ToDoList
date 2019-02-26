package com.exercise.todolist.ui

import android.content.Context
import android.databinding.DataBindingUtil
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
    context: Context,
    private val itemViewModel: ItemViewModel
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<Item>() // Cached copy of items

    override fun onCreateViewHolder(recyclerView: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(recyclerView.context)
        val itemView = inflater.inflate(R.layout.todo_item, recyclerView, false)
        //val binding = DataBindingUtil.setContentView(recyclerView.context, R.layout.todo_item)
        //val binding = DataBindingUtil.setContentView(recyclerView.context, R.layout.todo_item)
        val binding = TodoItemBinding.inflate(inflater)
        return ItemViewHolder(itemView, binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(items[position])


    internal fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(itemView: View, val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val showDone: CheckBox = itemView.findViewById(R.id.showDone)
        fun bind(current : Item) {
            binding.apply {
                viewModel = itemViewModel
                item = current
            }

            showDone.setOnCheckedChangeListener { buttonView, isChecked ->
                itemViewModel.update(items[position], isChecked)
            }


                //val binding = DataBindingUtil.setContentView(holder.itemView, R.layout.todo_item)
                /*with(apply) {
                    showTitle.text = current.title
                    showDescription.text = current.description
                    showDone.isChecked = current.done
                    showDone.setOnCheckedChangeListener { buttonView, isChecked ->
                        itemViewModel.update(items[position], isChecked)
                    }
                }*/
        }
        /*private val binding: com.exercise.todolist.databinding.TodoItemBinding
        val showDone: CheckBox = itemView.findViewById(R.id.showDone)
        val showTitle: TextView = itemView.findViewById(R.id.showTitleDone)
        val showDescription: TextView = itemView.findViewById(R.id.showDescription)*/
    }

}

