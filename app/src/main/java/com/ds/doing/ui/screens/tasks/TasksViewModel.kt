package com.ds.doing.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.repos.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    private val _tasks: MutableStateFlow<TasksState> = MutableStateFlow(TasksState())
    private var job: Job? = null
    val tasks = _tasks.asStateFlow()

    fun refreshTaskList(taskStatus: TaskStatus? = null) {
        job?.cancel()
        job = viewModelScope.launch {
            taskRepository.getTasksTask().collect { tasks ->
                _tasks.update { state ->
                    taskStatus?.let { taskFilter ->
                        state.copy(
                            tasks = tasks.filter { it.status == taskFilter }
                        )
                    } ?: state.copy(
                        tasks = tasks
                    )
                }
            }
        }
    }

    fun setTaskStatus(taskToUndate: Task, status: TaskStatus) {
        taskRepository.setTaskStatus(taskToUndate, status)
    }

    fun deleteTask(task: Task) {
        taskRepository.deleteTask(task)
    }
}
