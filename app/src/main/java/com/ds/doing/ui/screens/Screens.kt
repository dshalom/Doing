package com.ds.doing.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    object TasksScreen : Screen(
        route = "tasks",
        title = "Tasks",
        selectedIcon = Icons.Filled.List,
        unSelectedIcon = Icons.Outlined.List
    )

    object ProfileScreen : Screen(
        route = "profile",
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )
}

val items = listOf(
    Screen.TasksScreen,
    Screen.ProfileScreen
)
