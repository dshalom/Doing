package com.ds.doing.data.repos

import com.ds.doing.di.DBRepository
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.repos.TaskRepository
import data.TaskQueries
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TasKRepositoryImpl @Inject constructor(
    private val taskQueries: TaskQueries
) : TaskRepository {
    override fun addTask(title: String, description: String, dateDue: String) {
        taskQueries.insert(null, title, description, dateDue)
    }

    override fun deleteTask(task: Task) {
    }

    override fun setTaskStatus(taskToUpdate: Task, status: TaskStatus) {
    }

    override fun updateTask(taskToUpdate: Task) {
    }

    override fun getTasks(): StateFlow<List<Task>> {
        return MutableStateFlow(listOf<Task>())
    }
}