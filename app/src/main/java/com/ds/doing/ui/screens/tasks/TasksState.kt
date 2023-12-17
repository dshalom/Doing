package com.ds.doing.ui.screens.tasks

import com.ds.doing.domain.models.Task

data class TasksState(
    val tasks: List<Task> = listOf()
)
