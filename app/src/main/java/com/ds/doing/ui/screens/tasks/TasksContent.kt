package com.ds.doing.ui.screens.tasks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ds.doing.domain.models.Task
import com.ds.doing.domain.models.TaskStatus
import com.ds.doing.ui.screens.shared.MyBox

enum class Filter {
    All, ToDo, InProgress, InReview, Done
}

@Composable
fun TasksContent(
    viewModel: TasksViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel(),
    onAddTaskClicked: () -> Unit,
    onEditTaskClicked: (Task) -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(horizontal = 8.dp),
        topBar = {
            DoingSearch(searchViewModel)
        }

    ) { paddingValues ->

        val state by viewModel.state.collectAsState()

        LaunchedEffect(key1 = null) {
            viewModel.refreshTaskList()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tasks",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                item {
                    TaskChips(
                        titles = Filter.values(),
                        modifier = Modifier
                    ) { filter ->

                        when (filter) {
                            Filter.All -> {
                                viewModel.refreshTaskList()
                            }

                            Filter.ToDo -> {
                                viewModel.refreshTaskList(TaskStatus.Todo)
                            }

                            Filter.InProgress -> {
                                viewModel.refreshTaskList(TaskStatus.InProgress)
                            }

                            Filter.InReview -> {
                                viewModel.refreshTaskList(TaskStatus.Testing)
                            }

                            Filter.Done -> {
                                viewModel.refreshTaskList(TaskStatus.Done)
                            }
                        }
                    }
                }
                taskList(
                    state.tasks,
                    // todo this should allow task to be edited
                    onLongClicked = { task ->
                        onEditTaskClicked(task)
                    },
                    onMoreClicked = { task ->
                        viewModel.setTaskToEdit(task)
                    }
                )
            }

            FloatingActionButton(
                shape = CircleShape,
                onClick = onAddTaskClicked,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "icon")
            }
        }
        state.task?.also {
            TaskStatusBottomSheet(viewModel, it) {
                viewModel.setTaskToEdit(null)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.taskList(
    taskList: List<Task>,
    onMoreClicked: (Task) -> Unit,
    onLongClicked: (Task) -> Unit
) {
    items(taskList) { task ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .combinedClickable(onLongClick = { onLongClicked(task) }, onClick = {}),

            verticalAlignment = Alignment.CenterVertically
        ) {
            MyBox(task)

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onMoreClicked(task)
                    },
                contentAlignment = Alignment.CenterEnd

            ) {
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
    titles: Array<Filter>,
    modifier: Modifier = Modifier,
    onTaskTypeSelected: (Filter) -> Unit
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
            TaskChip(title.name, selectedIndex == index) {
                selectedIndex = index
                onTaskTypeSelected(title)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskChip(title: String, selected: Boolean, onClicked: () -> Unit) {
    ElevatedFilterChip(selected = selected, onClick = {
        onClicked()
    }, label = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DoingSearch(searchViewModel: SearchViewModel) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val resultTasks by searchViewModel.resultTasks.collectAsState()

    SearchBar(
        query = query,
        onQueryChange = {
            query = it
            searchViewModel.onSearchTextChanged(it)
        },
        onSearch = {
        },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        }

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(resultTasks) { task ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    MyBox(task = task)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskStatusBottomSheet(viewModel: TasksViewModel, task: Task, onDismissRequest: () -> Unit) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = "Change status",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(12.dp))
            val statusList = listOf(
                TaskStatus.Todo,
                TaskStatus.InProgress,
                TaskStatus.Testing,
                TaskStatus.Done
            )

            Row(
                Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                statusList.forEach { status ->
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier.clickable {
                            viewModel.setTaskStatus(task, status)
                            onDismissRequest()
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(
                                    color = status.color
                                ),
                            contentAlignment = Alignment.Center

                        ) {
                            Icon(
                                modifier = Modifier.size(38.dp),
                                imageVector = status.icon,
                                contentDescription = "Add Subject"
                            )
                        }
                        Text(
                            text = status.title,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    viewModel.deleteTask(task)
                    onDismissRequest()
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Delete task",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
