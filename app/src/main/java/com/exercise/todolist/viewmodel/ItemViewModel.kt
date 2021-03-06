package com.exercise.todolist.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.exercise.todolist.model.Item
import com.exercise.todolist.model.ItemRoomDatabase
import com.exercise.todolist.repository.ItemRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: ItemRepository
    var allItems: LiveData<List<Item>>
    var size: Int = 0
        get() = repository.allItems.value?.size ?: allItems.value?.size ?: 0

    init {
        val wordsDao = ItemRoomDatabase.getDatabase(application, scope).itemDao()
        repository = ItemRepository(wordsDao)
        allItems = repository.allItems
        Log.v("allItems", allItems.value.toString())
    }

    fun insert(item: Item) = scope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun update(item: Item) = scope.launch(Dispatchers.IO) {
        repository.update(item)
    }

    fun update(item: Item, isChecked: Boolean) {
        val newItem = item.apply {
            done = isChecked
        }
        update(newItem)
    }

    fun delete(position: Int) = scope.launch(Dispatchers.IO) {
        allItems.value?.apply {
            repository.delete(get(position))
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    operator fun get(position: Int): Item {
        return repository.allItems.value?.get(position) ?: allItems.value?.get(position) ?: Item.NONE
    }

    /*operator fun set(position: Int, item: Item) {
        allItems.value?.apply {
            repository.update(get(position))
        }
        update(position[position])
        delete(position)
        insert(item)
    }*/
}