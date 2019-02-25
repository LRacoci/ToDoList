package com.exercise.todolist.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.exercise.todolist.model.Item

@Dao
interface ItemDao {

    @Query("SELECT * from items_table ORDER BY item_id ASC")
    fun getAll(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Query("DELETE FROM items_table")
    fun deleteAll()

    @Delete
    fun delete(item: Item)
}