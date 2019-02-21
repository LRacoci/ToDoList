package com.exercise.todolist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: ItemRepository
    val allItems: LiveData<List<Item>>

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
}