package com.hasan.todos.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hasan.todos.components.InputField
import com.hasan.todos.components.TodoButton
import com.hasan.todos.model.Note
import com.hasan.todos.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    todos: List<Note>,
    onAddTodo: (Note) -> Unit,
    onRemoveTodo: (Note) -> Unit

){
    var todo by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(com.hasan.todos.R.string.app_name),
                fontWeight = FontWeight.Bold)
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon")
            }, colors = TopAppBarDefaults.topAppBarColors(Color(0xFFDADFE3)))


        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            InputField(
                modifier = Modifier.padding(top = 9.dp,
                    bottom = 8.dp),
                text = todo,
                label = "Todo",
                onTextChange = {
                    todo = it
                })

            TodoButton(
                text = "Save",
                onClick = {
                    if (todo.isNotEmpty()){
                        onAddTodo(Note(note = todo))
                        todo = ""
                        Toast.makeText(context,"TODO Added",
                            Toast.LENGTH_SHORT).show()
                    }
                })


        }
        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn {
            items(todos){todo->
                TodoRow(todos = todo,
                    onIconClick = {onRemoveTodo(it)})
            }
        }


    }
}

//@Preview
@Composable
fun TodoRow(
    modifier: Modifier = Modifier,
    onChecked:(Boolean) -> Unit = {},
    todos: Note,
    onIconClick: (Note) -> Unit
    ){
    val checkedState = remember {
        mutableStateOf(false)
    }
    Card(
        modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(percent = 50))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(if (checkedState.value){
            Color(0xFF9FD1E5)
        } else{Color(0xFF3B80EA)
        }),
        elevation = CardDefaults.cardElevation(10.dp)) {
        Row {
            Checkbox(modifier = Modifier
                .padding(start = 5.dp)
                .size(70.dp),
                checked = checkedState.value,
                onCheckedChange = { isChecked ->
                    checkedState.value = isChecked
                    onChecked(isChecked)
                }

            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = todos.note,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = formatDate(todos.entryDate.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
            }

            if (checkedState.value){
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Delete todo",
                    modifier = Modifier.padding(top = 8.dp, end = 7.dp)
                        .size(27.dp).clickable{onIconClick(todos)},
                    tint = Color.Red)
            }



        }
    }
}