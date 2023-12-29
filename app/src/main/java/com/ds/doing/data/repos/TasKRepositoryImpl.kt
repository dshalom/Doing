package com.ds.doing.data.repos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ds.doing.di.IoDispatcher
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.repos.TaskRepository
import data.TaskQueries
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasKRepositoryImpl @Inject constructor(
    private val taskQueries: TaskQueries,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : TaskRepository {

    private val _tasksFlow: MutableStateFlow<List<Task>> = MutableStateFlow(listOf())
    private val tasksFlow = _tasksFlow.asStateFlow()
    private lateinit var coroutineScope: CoroutineScope
    override fun addTask(
        title: String,
        description: String,
        status: TaskStatus,
        dateDue: String
    ) {
        taskQueries.insert(null, title, description, status, dateDue)
    }
    override fun deleteTask(task: Task) {
        taskQueries.delete(task.id.toLong())
    }

    override fun setTaskStatus(taskToUpdate: Task, status: TaskStatus) {
        taskQueries.updateStatus(status, taskToUpdate.id.toLong())
    }

    override fun updateTask(taskToUpdate: Task) {
        taskQueries.updateTask(
            taskToUpdate.status,
            taskToUpdate.title,
            taskToUpdate.description,
            taskToUpdate.dateDue,
            taskToUpdate.id.toLong()
        )
    }

    override fun getTasks(): StateFlow<List<Task>> {
        return tasksFlow
    }

    override fun init(coroutineScope: CoroutineScope) {
        coroutineScope.launch(dispatcher) {
            taskQueries.getTasks()
                .asFlow()
                .mapToList(dispatcher).collect { nd ->
                    _tasksFlow.update {
                        nd.map {
                            Task(
                                it.id.toInt(),
                                it.title,
                                it.description,
                                it.status ?: TaskStatus.Todo,
                                it.dateDue
                            )
                        }
                    }
                }
        }
    }
}
