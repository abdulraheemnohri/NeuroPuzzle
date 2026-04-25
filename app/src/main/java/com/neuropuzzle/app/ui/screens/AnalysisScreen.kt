package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neuropuzzle.app.ui.components.StatsCard

@Composable
fun AnalysisScreen(strategy: String, avgTime: Float, mistakeRate: Float) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Player Insights", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            StatsCard("Strategy", strategy)
            StatsCard("Avg Time", "${"%.1f".format(avgTime)}s")
        }
        StatsCard("Mistake Rate", "${"%.1f".format(mistakeRate * 100)}%")

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Adaptive Comment: You rely on repetition. Future puzzles will disrupt patterns.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
