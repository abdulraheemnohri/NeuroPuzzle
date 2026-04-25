package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    mistakes: Int,
    time: String,
    xpGained: Int = 50,
    level: Int = 1,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Puzzle Solved!", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Mistakes: $mistakes")
        Text(text = "Time: $time")

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "XP Gained: +$xpGained", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.secondary)
        Text(text = "Neural Level: $level")

        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth(0.6f)) {
            Text("Next Challenge")
        }
    }
}
