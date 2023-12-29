package com.ds.doing.ui.screens.addtask

import androidx.lifecycle.ViewModel
import com.ds.doing.di.DBRepository
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.repos.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTasksViewModel @Inject constructor(
    @DBRepository
    private val taskRepository: TaskRepository
) : ViewModel() {

    fun addTask(title: String, description: String, dateDue: String) {
        taskRepository.addTask(title, description, TaskStatus.Todo, dateDue)
    }

    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }
}
