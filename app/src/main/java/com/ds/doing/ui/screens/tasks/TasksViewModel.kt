package com.ds.doing.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.repos.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(val taskRepository: TaskRepository) : ViewModel() {

    val _tasks: MutableStateFlow<TasksState> = MutableStateFlow(TasksState())
    val tasks = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            taskRepository.getTasksTask().collect {
                _tasks.update { state ->
                    state.copy(
                        tasks = it
                    )
                }
            }
        }
    }

    fun deleteTask(task: Task) {
        taskRepository.deleteTask(task)
    }
}
