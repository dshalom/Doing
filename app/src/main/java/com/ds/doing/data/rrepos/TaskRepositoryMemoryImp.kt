package com.ds.doing.data.rrepos

import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.repos.TaskRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

class TaskRepositoryMemoryImp @Inject constructor() : TaskRepository {

    private val _tasksFlow: MutableStateFlow<List<Task>> = MutableStateFlow(mutableListOf())
    private val tasksFlow = _tasksFlow.asStateFlow()
    override fun addTask(task: Task) {
        _tasksFlow.update { state ->
            state + listOf(
                Task(9, "gym", TaskStatus.InProgress, "12/11/2013"),
                Task(9, "get milk", TaskStatus.Done, "12/11/2013"),
                Task(9, "jog", TaskStatus.Todo, "12/11/2013")
            )
        }
    }

    override fun deleteTask(task: Task) {
        _tasksFlow.update { state ->
            state + listOf(
                Task(9, "jj", TaskStatus.InProgress, "12/11/2013"),
                Task(2, "jbbj", TaskStatus.Done, "12/11/2013")
            )
        }
    }

    override fun getTasksTask(): StateFlow<List<Task>> {
        return tasksFlow
    }

    override fun printInfo() {
        Timber.i("dsds hashCode =  ${hashCode()}")
    }
}