package com.exercise.todolist.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.exercise.todolist.viewmodel.ItemViewModel
import com.exercise.todolist.R
import com.exercise.todolist.model.Item

class ItemListAdapter internal constructor(
    context: Context,
    private val itemViewModel: ItemViewModel
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<Item>() // Cached copy of items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = inflater.inflate(R.layout.todo_item, parent, false)
        val itemHolder = ItemViewHolder(itemView)
        return itemHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = itemViewModel[position] //?: items[position]
        current?.let{
            // holder.showDescription.visibility = View.GONE
            holder.showTitle.text = current.title
            holder.showDescription.text = current.description
            holder.showDone.isChecked = current.done
            holder.showDone.setOnCheckedChangeListener { _, isChecked ->
                val newItem = items[position].apply {
                    done = isChecked
                }
                itemViewModel.update(newItem)
            }
        }
    }


    internal fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val showDone: CheckBox = itemView.findViewById(R.id.showDone)
        val showTitle: TextView = itemView.findViewById(R.id.showTitleDone)
        val showDescription: TextView = itemView.findViewById(R.id.showDescription)
    }

}