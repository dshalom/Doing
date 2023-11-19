package com.ds.doing.ui.screens.addtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewTaskContent(onBackPressed: () -> Unit) {
    Scaffold(
        topBar = { AddTaskTopBar(onBackPressed = onBackPressed) },
        bottomBar = { AddTaskBottomBar() }

    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskTopBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = {}, // no title
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    // internal hamburger menu
                    Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        }
    )
}

@Composable
fun AddTaskBottomBar() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(32.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = "Add Task",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
