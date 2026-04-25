package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
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
        Spacer(modifier = Modifier.height(32.dp))

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
