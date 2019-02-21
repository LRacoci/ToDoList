package com.exercise.todolist

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ItemListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<Item>() // Cached copy of items


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val showTitleDone: TextView = itemView.findViewById(R.id.showTitleDone)
        val showDescription: TextView = itemView.findViewById(R.id.showDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = inflater.inflate(R.layout.todo_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = items[position]
        holder.showTitleDone.text = current.title
        holder.showDescription.text = current.description
    }

    internal fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

}