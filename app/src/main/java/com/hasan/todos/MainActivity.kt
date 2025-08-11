package com.hasan.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.hasan.todos.screen.TodoScreen
import com.hasan.todos.screen.TodoViewModel
import com.hasan.todos.ui.theme.TODOSTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOSTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TodoApp(todoViewModel)
                }
            }
        }
    }
}

@Composable
fun TodoApp(viewModel: TodoViewModel) {
    val todoList = viewModel.todoList.collectAsState(initial = emptyList()).value

    TodoScreen(
        todos = todoList,
        onAddTodo = { viewModel.addTodo(it) },
        onRemoveTodo = { viewModel.removeTodoById(it.id) }
    )
}
