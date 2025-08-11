package com.hasan.todos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hasan.todos.model.Note
import kotlinx.coroutines.flow.Flow
import java.util.UUID


@Dao
interface TodoDataBaseDao {

    @Query("SELECT * from todo_tbl")
    fun getTodo():
            Flow<List<Note>>

    @Query("SELECT * from todo_tbl where id =:id")
    suspend fun getTodoById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from todo_tbl")
    suspend fun deleteAll()

    @Query("DELETE FROM todo_tbl WHERE id = :id")
    suspend fun deleteById(id: UUID)


    @Delete
    suspend fun deleteTodo(note: Note)

}