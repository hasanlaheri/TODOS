package com.hasan.todos.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hasan.todos.model.Note
import com.hasan.todos.util.DateConverter
import com.hasan.todos.util.UUIDConverter


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)

abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDataBaseDao
}