package com.exercise.todolist

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "items_table")
class Item(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "done")
    val done: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    var item_id : Int? = null
}
