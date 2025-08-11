package com.hasan.todos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID


@Entity(tableName = "todo_tbl")
data class Note(

    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "todo")
    val note: String,

    @ColumnInfo(name = "todo_entry_date")
    val entryDate: Date = Date.from(Instant.now())

)
