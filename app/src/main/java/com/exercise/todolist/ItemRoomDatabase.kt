package com.exercise.todolist

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [Item::class], version = 1)
public abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ItemRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "Word_database"
                )
                    .addCallback(ItemDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class ItemDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            fun populateDatabase(wordDao: ItemDao) {
                //wordDao.deleteAll()

                /*val item = Item("Title One", "This is one example of a description", false)
                wordDao.insert(item)*/
                //item = Item("Title Two", "This is anther example of a description", false)
            }
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.itemDao())
                    }
                }
            }
        }


    }
}