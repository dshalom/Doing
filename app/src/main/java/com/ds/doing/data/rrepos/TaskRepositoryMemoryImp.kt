package com.ds.doing.data.rrepos

import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.repos.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class TaskRepositoryMemoryImp @Inject constructor() : TaskRepository {

    private val _tasksFlow: MutableStateFlow<List<Task>> = MutableStateFlow(mutableListOf())
    private val tasksFlow = _tasksFlow.asStateFlow()
    override fun addTask(task: Task) {
        _tasksFlow.update { state ->
            state + listOf(
                task,
                Task("gym", TaskStatus.InProgress, "12/11/2013")
            )
        }
    }

    override fun deleteTask(task: Task) {
        _tasksFlow.update { state ->
            state - task
        }
    }

    override fun getTasksTask(): StateFlow<List<Task>> {
        return tasksFlow
    }
}
