package com.ds.doing.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ds.doing.domain.models.Task
import com.ds.doing.ui.screens.Screen
import com.ds.doing.ui.screens.items
import com.ds.doing.ui.screens.profile.ProfileContent
import com.ds.doing.ui.screens.tasks.TasksContent

@Composable
fun MainContent(onAddTaskClicked: () -> Unit, onEditTaskClicked: (Task) -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { DoingNavigationBar(navController) }

    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = Screen.TasksScreen.route,
            Modifier.padding(paddingValues)
        ) {
            composable(Screen.TasksScreen.route) {
                TasksContent(
                    onAddTaskClicked = onAddTaskClicked,
                    onEditTaskClicked = onEditTaskClicked
                )
            }
            composable(Screen.ProfileScreen.route) { ProfileContent() }
        }
    }
}

@Composable
fun DoingNavigationBar(navController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, screen ->
            val selected = selectedItem == index
            NavigationBarItem(
                selected = selected,
                onClick = {
                    selectedItem = index
                    /* navigate to selected route */
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) screen.selectedIcon else screen.unSelectedIcon,
                        contentDescription = screen.title
                    )
                },
                label = { Text(text = screen.title) }
            )
        }
    }
}
