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
    onStartChaos: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "NeuroPuzzle", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onStartGame, modifier = Modifier.fillMaxWidth(0.7f)) {
            Text("Adaptive Mode")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onStartExperiment, modifier = Modifier.fillMaxWidth(0.7f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
            Text("Experiment Mode")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onStartChaos, modifier = Modifier.fillMaxWidth(0.7f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
            Text("Chaos Mode")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onViewAnalysis, modifier = Modifier.fillMaxWidth(0.7f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)) {
            Text("Player Analysis")
        }
    }
}
