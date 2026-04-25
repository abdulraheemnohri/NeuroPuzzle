package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neuropuzzle.app.ui.components.StatsCard

@Composable
fun AnalysisScreen(strategy: String, avgTime: Float, mistakeRate: Float, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(onClick = onBack) {
            Text("< Back")
        }
        Text(text = "Intelligence Analysis", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            StatsCard("Strategy", strategy)
            StatsCard("Avg Time", "${"%.1f".format(avgTime)}s")
        }
        StatsCard("Mistake Rate", "${"%.1f".format(mistakeRate * 100)}%")

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Game Counter-Strategy:",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = "You rely on repetition. Future puzzles will disrupt patterns by introducing unpredictable branching and hidden traps.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
