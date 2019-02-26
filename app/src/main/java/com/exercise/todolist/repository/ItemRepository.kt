package com.exercise.todolist.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.exercise.todolist.model.Item
import com.exercise.todolist.model.ItemDao

class ItemRepository(private val itemDao: ItemDao) {

    var allItems: LiveData<List<Item>> = itemDao.getAll()

    @WorkerThread
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }
    @WorkerThread
    suspend fun update(item: Item) {
        itemDao.update(item)
    }

    @WorkerThread
    suspend fun delete(item: Item) {
        itemDao.delete(item)
    }
}