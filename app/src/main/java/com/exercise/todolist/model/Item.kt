package com.exercise.todolist.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "items_table")
class Item(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "done")
    var done: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    var item_id : Int? = null
}
