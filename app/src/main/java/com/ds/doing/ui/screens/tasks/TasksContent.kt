package com.ds.doing.ui.screens.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.domain.models.testTasks

@Composable
fun TasksContent() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                DoingSearch()
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tasks",
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            item {
                TaskChips(
                    titles = listOf("All", "To do", "In Progress", "In Review", "Done"),
                    modifier = Modifier
                ) {

                }
            }
            taskList(testTasks)
        }
        FloatingActionButton(
            shape = CircleShape,
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "icon")
        }
    }

}

fun LazyListScope.taskList(taskList: List<Task>) {
    items(taskList) { task ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(
                        color = when (task.status) {
                            TaskStatus.Done -> Color.Green
                            TaskStatus.InProgress -> Color.Yellow
                            TaskStatus.InReview -> Color.Magenta
                            TaskStatus.Todo -> Color.Blue
                        }
                    ),
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    modifier = Modifier.size(38.dp),
                    imageVector = when (task.status) {
                        TaskStatus.Done -> Icons.Default.Done
                        TaskStatus.InProgress -> Icons.Default.DirectionsWalk
                        TaskStatus.InReview -> Icons.Default.BugReport
                        TaskStatus.Todo -> Icons.Default.EditNote
                    },
                    contentDescription = "Add Subject")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = task.dateDue,
                    style = MaterialTheme.typography.headlineSmall
                )
            }


            Box(modifier = Modifier.size(30.dp),
                contentAlignment = Alignment.CenterEnd) {

                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = "More"
                )
            }
        }

    }


}

@Composable
fun TaskChips(
    titles: List<String>,
    modifier: Modifier = Modifier,
    onTaskTypeSelected: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        titles.forEachIndexed { index, title ->
            TaskChip(title, selectedIndex == index) {
                selectedIndex = index
                onTaskTypeSelected(index)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskChip(title: String, selected: Boolean, onClicked: () -> Unit) {
    ElevatedFilterChip(
        selected = selected,
        onClick = {
            onClicked()
        },
        label = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DoingSearch() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory = listOf("Android", "Kotlin", "Compose", "Material Design", "GPT-4")

    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { newQuery ->
            println("Performing search on query: $newQuery")
        },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },

        ) {
        searchHistory.takeLast(3).forEach { item ->
            ListItem(modifier = Modifier.clickable { query = item },
                headlineContent = {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) })
        }
    }

}