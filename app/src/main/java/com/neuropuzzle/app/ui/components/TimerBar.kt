package com.neuropuzzle.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimerBar(progress: Float, timeText: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Time: $timeText")
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
    }
}
