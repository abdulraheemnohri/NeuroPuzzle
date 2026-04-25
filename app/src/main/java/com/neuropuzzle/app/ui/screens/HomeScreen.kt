package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neuropuzzle.app.data.models.PlayerProfile

@Composable
fun HomeScreen(
    profile: PlayerProfile,
    onStartGame: () -> Unit,
    onViewAnalysis: () -> Unit,
    onStartExperiment: () -> Unit,
    onStartChaos: () -> Unit,
    onViewProfile: () -> Unit,
    onViewSettings: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "NeuroPuzzle", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Level ${profile.currentLevel}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.secondary)

        val xpProgress = profile.experiencePoints.toFloat() / (profile.currentLevel * 100).toFloat()
        LinearProgressIndicator(
            progress = xpProgress,
            modifier = Modifier.fillMaxWidth(0.6f).padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onStartGame, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Adaptive Mode")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(0.8f)) {
            Button(onClick = onStartExperiment, modifier = Modifier.weight(1f).padding(end = 4.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text("Experiment")
            }
            Button(onClick = onStartChaos, modifier = Modifier.weight(1f).padding(start = 4.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                Text("Chaos")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = onViewAnalysis, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Intelligence Analysis")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(0.8f)) {
            OutlinedButton(onClick = onViewProfile, modifier = Modifier.weight(1f).padding(end = 4.dp)) {
                Text("Profile")
            }
            OutlinedButton(onClick = onViewSettings, modifier = Modifier.weight(1f).padding(start = 4.dp)) {
                Text("Settings")
            }
        }
    }
}
