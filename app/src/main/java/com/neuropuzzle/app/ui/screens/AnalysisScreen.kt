package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neuropuzzle.app.data.models.PlayerProfile
import com.neuropuzzle.app.ui.components.StatsCard

@Composable
fun AnalysisScreen(profile: PlayerProfile, onBack: () -> Unit) {
    val strategy = profile.strategyType
    val avgTime = profile.avgSolveTime / 1000f
    val mistakeRate = profile.mistakeRate

    val feedback = when (strategy) {
        "repetitive" -> "You rely on repetition. Future puzzles will disrupt patterns by introducing unpredictable branching and hidden traps."
        "bruteforce" -> "You tend to use brute force. The system will counter this by limiting your move count and increasing deception."
        "logical" -> "You demonstrate logical precision. The complexity will scale to challenge your strategic depth."
        else -> "Continue playing to allow the system to classify your strategic archetype."
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(onClick = onBack) {
            Text("< Back")
        }
        Text(text = "Intelligence Analysis", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            StatsCard("Archetype", strategy.uppercase())
            StatsCard("Avg Time", "${"%.1f".format(avgTime)}s")
        }
        Row {
            StatsCard("Mistake Rate", "${"%.1f".format(mistakeRate * 100)}%")
            StatsCard("Adaptability", "${"%.2f".format(profile.adaptabilityScore)}")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Adaptive Insights:",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = feedback,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = (profile.skillLevel / 10f).coerceAtMost(1f),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(text = "Neural Maturity: ${profile.skillLevel}/10", style = MaterialTheme.typography.labelSmall)
    }
}
