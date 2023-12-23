package com.ds.doing.data.rrepos

import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.repos.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class TaskRepositoryMemoryImp @Inject constructor() : TaskRepository {

    private val _tasksFlow: MutableStateFlow<List<Task>> = MutableStateFlow(getTestData())
    private val tasksFlow = _tasksFlow.asStateFlow()
    override fun addTask(task: Task) {
        _tasksFlow.update { state ->
            state + task
        }
    }

    override fun deleteTask(task: Task) {
        _tasksFlow.update { state ->
            state - task
        }
    }

    override fun setTaskStatus(taskToUpdate: Task, status: TaskStatus) {
        _tasksFlow.update { state ->
            state.map {
                if (it == taskToUpdate) {
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
                if (it == taskToUpdate) {
                    taskToUpdate
                } else {
                    it
                }
            }
        }
    }

    override fun getTasksTask(): StateFlow<List<Task>> {
        return tasksFlow
    }
}

fun getTestData(): List<Task> {
    return listOf(
        Task(
            "task 1",
            "dec1 ",
            TaskStatus.Todo,
            "sometime"
        ),
        Task(
            "task 2",
            "dec2 ",

            TaskStatus.InProgress,
            "sometime"
        ),
        Task(
            "task 3",
            "dec3 ",

            TaskStatus.Testing,
            "sometime"
        ),
        Task(
            "task 4",
            "dec4",

            TaskStatus.Done,
            "sometime"
        )
    )
}
