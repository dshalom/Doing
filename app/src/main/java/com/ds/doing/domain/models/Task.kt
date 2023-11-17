package com.ds.doing.domain.models

sealed class TaskStatus {
    object Todo : TaskStatus()
    object InProgress : TaskStatus()
    object InReview : TaskStatus()
    object Done : TaskStatus()
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
        status = TaskStatus.InReview,
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
        status = TaskStatus.InReview,
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
