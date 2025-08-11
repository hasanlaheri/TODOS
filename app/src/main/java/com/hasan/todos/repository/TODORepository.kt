package com.hasan.todos.repository

import com.hasan.todos.data.TodoDataBaseDao
import com.hasan.todos.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class TODORepository @Inject constructor(private val todoDataBaseDao: TodoDataBaseDao) {
    suspend fun addTodo(note: Note) = todoDataBaseDao.insert(note)

    suspend fun updateTodo(note: Note) = todoDataBaseDao.update(note)

    suspend fun deleteTodoById(id: UUID) = todoDataBaseDao.deleteById(id)


    suspend fun deleteTodo(note: Note) = todoDataBaseDao.deleteTodo(note)

    suspend fun deleteAllTodo() = todoDataBaseDao.deleteAll()

    fun getAllTodos(): Flow<List<Note>> = todoDataBaseDao.getTodo().flowOn(Dispatchers.IO)
}