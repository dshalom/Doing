package com.ds.doing.ui.screens.addtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewTaskContent(onBackPressed: () -> Unit) {
    var taskState by rememberNewTaskState(NewTaskState("", ""))
    Scaffold(
        topBar = { AddTaskTopBar(onBackPressed = onBackPressed) },
        bottomBar = { AddTaskBottomBar() }

    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TaskName(
                modifier = Modifier.wrapContentSize(),
                "Name",
                taskState.title
            ) { s ->
                taskState = NewTaskState(
                    title = s,
                    description = taskState.description
                )
            }

            TaskName(
                modifier = Modifier
                    .height(200.dp),
                "Description",
                taskState.description
            ) { s ->
                taskState = NewTaskState(
                    title = taskState.title,
                    description = s
                )
            }
        }
    }
}
class NewTaskState(var title: String, var description: String)

@Composable
private fun rememberNewTaskState(mc: NewTaskState): MutableState<NewTaskState> {
    return remember {
        mutableStateOf(mc)
    }
}

@Composable
private fun TaskName(
    modifier: Modifier,
    title: String,
    t: String,
    c: (String) -> Unit
) {
    OutlinedTextField(
        value = t,
        label = {
            Text(text = title)
        },
        modifier = modifier.fillMaxWidth(),

        onValueChange = {
            c(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskTopBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = {}, // no title
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    // internal hamburger menu
                    Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        }
    )
}

@Composable
fun AddTaskBottomBar() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = "Add Task",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
