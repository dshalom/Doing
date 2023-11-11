package com.ds.doing.ui.screens.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksContent() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        FloatingActionButton(

            shape = CircleShape,
            onClick = {},
            modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "icon")
        }
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

        }
    }

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