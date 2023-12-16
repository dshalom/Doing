package com.ds.doing.domain.repos

import com.ds.doing.domain.models.Task
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    fun addTask(task: Task)
    fun deleteTask(task: Task)
    fun getTasksTask(): StateFlow<List<Task>>

    fun printInfo()
}