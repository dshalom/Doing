package com.ds.doing.domain.repos

import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    fun addTask(task: Task)
    fun deleteTask(task: Task)

    fun setTaskStatus(taskToUpdate: Task, status: TaskStatus)
    fun updateTask(taskToUpdate: Task)

    fun getTasksTask(): StateFlow<List<Task>>
}
