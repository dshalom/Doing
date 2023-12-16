package com.ds.doing.ui.screens.addtask

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.repos.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddTasksViewModel @Inject constructor(val taskRepository: TaskRepository) : ViewModel() {

   init {
       taskRepository.printInfo()
   }

    fun addTask(task: Task){
        taskRepository.addTask(task)
    }

}