package com.ds.doing.domain.repos

import com.ds.doing.data.rrepos.TasksHolder
import com.ds.doing.domain.models.Task
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    fun addTask(task: Task)
    fun deleteTask(task: Task)
    fun getTasksTask(): StateFlow<TasksHolder>

    fun printInfo()
}