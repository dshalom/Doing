package com.ds.doing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ds.doing.ui.screens.addtask.AddTaskActivity
import com.ds.doing.ui.screens.main.MainContent
import com.ds.doing.ui.theme.DoingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoingTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent(
                        onAddTaskClicked = {
                            startActivity(
                                AddTaskActivity.getAddTaskIntent(
                                    this
                                )
                            )
                        },
                        onEditTaskClicked = {
                            startActivity(
                                AddTaskActivity.getEditTaskIntent(
                                    this,
                                    it
                                )
                            )
                        }

                    )
                }
            }
        }
    }
}
