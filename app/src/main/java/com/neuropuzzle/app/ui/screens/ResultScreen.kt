package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(mistakes: Int, time: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Puzzle Solved!", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Mistakes: $mistakes")
        Text(text = "Time: $time")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onNext) {
            Text("Next Puzzle")
        }
    }
}
