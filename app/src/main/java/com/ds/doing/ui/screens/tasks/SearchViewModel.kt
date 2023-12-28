package com.ds.doing.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.doing.di.InMemoryRepository
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.matchesSearch
import com.ds.doing.domain.repos.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    @InMemoryRepository
    repository: TaskRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _tasks: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasks = _tasks.asStateFlow()

    val resultTasks = searchText
        .combine(_tasks) { text, tasks ->
            if (text.isEmpty()) {
                tasks
            } else {
                tasks.filter {
                    it.matchesSearch(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _tasks.value
        )

    init {
        viewModelScope.launch {
            repository.getTasks().collect {
                _tasks.value = it
            }
        }
    }

    fun onSearchTextChanged(searchText: String) {
        _searchText.update { searchText }
    }
}
