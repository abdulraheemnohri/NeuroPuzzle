package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neuropuzzle.app.data.models.PlayerProfile
import com.neuropuzzle.app.ui.components.StatsCard

@Composable
fun ProfileScreen(profile: PlayerProfile, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(onClick = onBack) {
            Text("< Back")
        }
        Text("Player Profile", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Status: Active", style = MaterialTheme.typography.titleMedium)
                Text("Skill Level: ${profile.skillLevel}")
                Text("Adaptability: ${"%.2f".format(profile.adaptabilityScore)}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Recent Achievements", style = MaterialTheme.typography.titleLarge)

        LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            item {
                StatsCard("Total Games", "42")
            }
            item {
                StatsCard("Success Rate", "88%")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Strategy: ${profile.strategyType.uppercase()}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
