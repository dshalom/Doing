package com.ds.doing.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import timber.log.Timber

sealed class TaskStatus(val title: String, val icon: ImageVector, val color: Color) {
    object Todo : TaskStatus(title = "To do", Icons.Default.EditNote, color = Color.Blue)
    object InProgress :
        TaskStatus(title = "In progress", Icons.Default.DirectionsWalk, color = Color.Yellow)

    object Testing : TaskStatus(title = "Testing", Icons.Default.BugReport, color = Color.Magenta)
    object Done : TaskStatus(title = "Done", icon = Icons.Default.Done, color = Color.Green)
}

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    var status: TaskStatus,
    val dateDue: String
)

fun Task.matchesSearch(searchText: String): Boolean {
    val matchingCombinations = listOf(
        title,
        description
    )
    return matchingCombinations.any {
        val r = it.contains(
            searchText,
            ignoreCase = true
        )
        Timber.i("dsds looking if $it contains $searchText  r=$r")

        return r
    }
}
