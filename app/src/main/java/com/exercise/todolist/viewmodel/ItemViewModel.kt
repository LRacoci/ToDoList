package com.exercise.todolist.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
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

    init {
        val wordsDao = ItemRoomDatabase.getDatabase(application, scope).itemDao()
        repository = ItemRepository(wordsDao)
        allItems = repository.allItems
    }

    fun insert(item: Item) = scope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun delete(position: Int) = scope.launch(Dispatchers.IO) {
        repository.delete(position)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    operator fun get(position: Int): Item? {
        return repository.allItems.value?.get(position)
    }

    operator fun set(position: Int, item: Item) {
        delete(position)
        insert(item)
    }
}