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

data class TasksHolder(val tasks: List<Task>)
class TaskRepositoryMemoryImp @Inject constructor() : TaskRepository {

    private val _tasksFlow: MutableStateFlow<TasksHolder> = MutableStateFlow(TasksHolder(mutableListOf()))
    private val tasksFlow = _tasksFlow.asStateFlow()
    override fun addTask(task: Task) {
        _tasksFlow.update { state ->
            state.copy(
                tasks = state.tasks + Task(9, "jj", TaskStatus.Done, "")
            )

        }
    }

    override fun deleteTask(task: Task) {
        _tasksFlow.update { state ->
            state.copy(
                tasks = state.tasks - Task(9, "jj", TaskStatus.Done, "")
            )

        }
    }

    override fun getTasksTask(): StateFlow<TasksHolder> {
        return tasksFlow
    }

    override fun printInfo() {
        Timber.i("dsds hashCode =  ${hashCode()}")
    }
}