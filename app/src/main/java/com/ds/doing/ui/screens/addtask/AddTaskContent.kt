package com.ds.doing.ui.screens.addtask

import androidx.compose.foundation.layout.Arrangement
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus

@Composable
fun NewTaskContent(
    viewModel: AddTasksViewModel = hiltViewModel(),
    onBackPressed: () -> Unit) {
    var taskState by rememberNewTaskState(NewTaskState("", ""))

    var t by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AddTaskTopBar(onBackPressed = onBackPressed) },
        bottomBar = {
            AddTaskBottomBar {
                viewModel.addTask(
                    Task(id = 9,
                        title = "title",
                        status = TaskStatus.Done,
                        dateDue = "data")
                )
                onBackPressed()
            }
        }

    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TaskItem(
                modifier = Modifier.wrapContentSize(),
                "Title",
                t
            ) { newTitle ->
                t = newTitle
            }

            TaskItem(
                modifier = Modifier
                    .height(360.dp),
                "Description",
                taskState.description
            ) { newDescription ->
                taskState = NewTaskState(
                    title = taskState.title,
                    description = newDescription
                )
            }

            TaskItem(
                modifier = Modifier.wrapContentSize(),
                "Date",
                taskState.description
            ) { newDescription ->
                taskState = NewTaskState(
                    title = taskState.title,
                    description = newDescription
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
private fun TaskItem(
    modifier: Modifier,
    label: String,
    title: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = title,
        label = {
            Text(text = label)
        },
        modifier = modifier.fillMaxWidth(),
        onValueChange = {
            onValueChanged(it)
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
fun AddTaskBottomBar(onAddTaskClicked: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        onClick = onAddTaskClicked
    ) {
        Text(
            text = "Add Task",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
