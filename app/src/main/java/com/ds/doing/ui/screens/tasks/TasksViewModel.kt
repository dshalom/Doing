package com.ds.doing.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.doing.di.DBRepository
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
class TasksViewModel @Inject constructor(
    @DBRepository
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _state: MutableStateFlow<TasksState> = MutableStateFlow(TasksState())
    private var job: Job? = null
    val state = _state.asStateFlow()

    init {
        taskRepository.addTask("a title", "a description", TaskStatus.Todo, "tomorrow")

        taskRepository.init(viewModelScope)
    }
    fun refreshTaskList(taskStatus: TaskStatus? = null) {
        job?.cancel()
        job = viewModelScope.launch {
            taskRepository.getTasks().collect { tasks ->
                _state.update { state ->
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

    fun setTaskToEdit(task: Task?) {
        _state.update {
            it.copy(
                task = task
            )
        }
    }
}
