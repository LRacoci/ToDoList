package com.exercise.todolist.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.exercise.todolist.model.Item
import com.exercise.todolist.model.ItemDao

class ItemRepository(private val itemDao: ItemDao) {

    var allItems: LiveData<List<Item>> = itemDao.getAll()

    @WorkerThread
    fun insert(item: Item) {
        itemDao.insert(item)
    }
    @WorkerThread
    fun delete(position: Int) {
        allItems.value?.apply {
            get(position).let {
                itemDao.delete(it)
            }
        }
    }
}