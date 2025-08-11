package com.hasan.todos.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasan.todos.model.Note
import com.hasan.todos.repository.TODORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TODORepository): ViewModel() {

    private val _todoList = MutableStateFlow<List<Note>>(emptyList())
    val todoList = _todoList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTodos().distinctUntilChanged()
                .collect { listOfTodos ->
                    if (listOfTodos.isEmpty()){
                        Log.d("Empty", ":Empty List ")
                    }else{
                        _todoList.value = listOfTodos
                    }


                }
        }
    }
    fun addTodo(note: Note) = viewModelScope.launch { repository.addTodo(note) }
    fun updateTodo(note: Note) = viewModelScope.launch { repository.updateTodo(note) }
    fun removeTodo(note: Note) = viewModelScope.launch { repository.deleteTodo(note) }
    fun removeTodoById(id: UUID) = viewModelScope.launch { repository.deleteTodoById(id) }


}