package com.ds.doing.domain.repos

import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    fun addTask(title: String, description: String, status: TaskStatus, dateDue: String)
    fun deleteTask(task: Task)

    fun setTaskStatus(taskToUpdate: Task, status: TaskStatus)
    fun updateTask(taskToUpdate: Task)

    fun getTasks(): StateFlow<List<Task>>
    fun init(coroutineScope: CoroutineScope)
}
