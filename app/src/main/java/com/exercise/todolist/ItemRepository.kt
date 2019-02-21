package com.exercise.todolist

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class ItemRepository(private val itemDao: ItemDao) {

    val allItems: LiveData<List<Item>> = itemDao.getAll()

    @WorkerThread
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }
}