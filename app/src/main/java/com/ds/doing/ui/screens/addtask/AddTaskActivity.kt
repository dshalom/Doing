package com.ds.doing.ui.screens.addtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ds.doing.domain.models.Task
import com.ds.doing.ui.theme.DoingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val id = intent.getIntExtra("id", 0)
                    val title = intent.getStringExtra("title")
                    val description = intent.getStringExtra("description")
                    val status = intent.getStringExtra("status")
                    val dateDue = intent.getStringExtra("dateDue")
                    NewTaskContent(
                        id = id,
                        title = title ?: "",
                        description = description ?: "",
                        status = status ?: "",
                        dateDue = dateDue ?: ""
                    ) {
                        finish()
                    }
                }
            }
        }
    }

    companion object {
        fun getAddTaskIntent(context: Context): Intent {
            return Intent(context, AddTaskActivity::class.java)
        }

        fun getEditTaskIntent(context: Context, task: Task): Intent {
            return Intent(context, AddTaskActivity::class.java).also {
                it.putExtra("id", task.id)
                it.putExtra("title", task.title)
                it.putExtra("description", task.description)
                it.putExtra("status", "status")
                it.putExtra("dateDue", task.dateDue)
            }
        }
    }
}
