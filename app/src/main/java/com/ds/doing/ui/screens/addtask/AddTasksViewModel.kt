package com.ds.doing.ui.screens.addtask

import androidx.lifecycle.ViewModel
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.repos.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTasksViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    fun getNextId() = taskRepository.getNewId()
    fun addTask(task: Task) {
        taskRepository.addTask(task)
    }

    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }
}
