package com.ds.doing.data.repos

import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.repos.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class TaskRepositoryMemoryImp @Inject constructor() : TaskRepository {

    private var id: Int = 10

    private val _tasksFlow: MutableStateFlow<List<Task>> = MutableStateFlow(getTestData())
    private val tasksFlow = _tasksFlow.asStateFlow()

    override fun addTask(
        title: String,
        description: String,
        status: TaskStatus,
        dateDue: String
    ) {
        ++id
        _tasksFlow.update { state ->
            state + Task(
                id = ++id,
                title = title,
                description = description,
                status = status,
                dateDue = dateDue
            )
        }
    }

    override fun deleteTask(task: Task) {
        _tasksFlow.update { state ->
            state.filter {
                it.id != task.id
            }
        }
    }

    override fun setTaskStatus(taskToUpdate: Task, status: TaskStatus) {
        _tasksFlow.update { state ->
            state.map {
                if (it.id == taskToUpdate.id) {
                    it.copy(status = status)
                } else {
                    it
                }
            }
        }
    }

    override fun updateTask(taskToUpdate: Task) {
        _tasksFlow.update { state ->
            state.map {
                if (it.id == taskToUpdate.id) {
                    taskToUpdate
                } else {
                    it
                }
            }
        }
    }

    override fun getTasks(): StateFlow<List<Task>> {
        return tasksFlow
    }

    override fun init(viewModelScope: CoroutineScope) {
    }
}

fun getTestData(): List<Task> {
    return listOf(
        Task(
            1,
            "task 1",
            "dec1 ",
            TaskStatus.Todo,
            "sometime"
        ),
        Task(
            2,
            "task 2",

            "dec2 ",
            TaskStatus.InProgress,
            "sometime"
        ),
        Task(
            3,
            "task 3",

            "dec3 ",
            TaskStatus.Testing,
            "sometime"
        ),
        Task(
            4,
            "task 4",

            "dec4",
            TaskStatus.Done,
            "sometime"
        )
    )
}
