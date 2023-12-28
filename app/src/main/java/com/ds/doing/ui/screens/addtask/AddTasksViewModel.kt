package com.ds.doing.ui.screens.addtask

import androidx.lifecycle.ViewModel
import com.ds.doing.di.InMemoryRepository
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.repos.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTasksViewModel @Inject constructor(
    @InMemoryRepository
    private val taskRepository: TaskRepository
) : ViewModel() {

    fun addTask(title: String, description: String, dateDue: String) {
        taskRepository.addTask(title, description, dateDue)
    }

    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }
}
