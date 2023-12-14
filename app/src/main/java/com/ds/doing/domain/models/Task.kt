package com.ds.doing.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TaskStatus(val title: String, val icon: ImageVector, val color: Color) {
    object Todo : TaskStatus(title = "To do", Icons.Default.EditNote, color = Color.Blue)
    object InProgress :
        TaskStatus(title = "In progress", Icons.Default.DirectionsWalk, color = Color.Yellow)

    object Testing : TaskStatus(title = "Testing", Icons.Default.BugReport, color = Color.Magenta)
    object Done : TaskStatus(title = "Done", icon = Icons.Default.Done, color = Color.Green)
}

data class Task(
    val id: Int,
    val title: String,
    val status: TaskStatus,
    val dateDue: String
)

val testTasks = listOf(
    Task(
        id = 0,
        title = "task1",
        status = TaskStatus.Todo,
        dateDue = "12/12/2023"
    ),
    Task(
        id = 0,
        title = "task2",
        status = TaskStatus.Done,
        dateDue = "12/12/2023"
    ),
    Task(
        id = 0,
        title = "task3",
        status = TaskStatus.Testing,
        dateDue = "12/12/2023"
    ),
    Task(
        id = 0,
        title = "task4",
        status = TaskStatus.InProgress,
        dateDue = "13/12/2023"
    ),
    Task(
        id = 0,
        title = "task5",
        status = TaskStatus.Todo,
        dateDue = "11/12/2023"
    ),
    Task(
        id = 0,
        title = "task6",
        status = TaskStatus.Done,
        dateDue = "2/12/2023"
    ),
    Task(
        id = 0,
        title = "task1",
        status = TaskStatus.Todo,
        dateDue = "12/12/2023"
    ),
    Task(
        id = 0,
        title = "task2",
        status = TaskStatus.Done,
        dateDue = "12/12/2023"
    ),
    Task(
        id = 0,
        title = "task3",
        status = TaskStatus.Testing,
        dateDue = "12/12/2023"
    ),
    Task(
        id = 0,
        title = "task4",
        status = TaskStatus.InProgress,
        dateDue = "13/12/2023"
    ),
    Task(
        id = 0,
        title = "task5 task5 task5 task5 task5 task5 task5",
        status = TaskStatus.Todo,
        dateDue = "11/12/2023"
    ),
    Task(
        id = 0,
        title = "task6",
        status = TaskStatus.Done,
        dateDue = "2/12/2023"
    )
)
