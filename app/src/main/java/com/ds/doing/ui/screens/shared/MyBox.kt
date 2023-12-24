package com.ds.doing.ui.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ds.doing.domain.models.Task

@Composable
fun RowScope.MyBox(task: Task) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(
                color = task.status.color
            ),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            modifier = Modifier.size(38.dp),
            imageVector = task.status.icon,
            contentDescription = "Add Subject"
        )
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
}
