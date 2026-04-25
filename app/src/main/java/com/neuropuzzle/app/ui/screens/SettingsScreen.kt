package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    var soundEnabled by remember { mutableStateOf(true) }
    var vibrationEnabled by remember { mutableStateOf(true) }
    var mutationIntensity by remember { mutableFloatStateOf(0.5f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(onClick = onBack) {
            Text("< Back")
        }
        Text("Settings", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Audio Feedback")
            Switch(checked = soundEnabled, onCheckedChange = { soundEnabled = it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Vibration")
            Switch(checked = vibrationEnabled, onCheckedChange = { vibrationEnabled = it })
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("Mutation Intensity: ${"%.1f".format(mutationIntensity)}")
        Slider(
            value = mutationIntensity,
            onValueChange = { mutationIntensity = it },
            valueRange = 0f..1f
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* Reset Profile logic */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Reset Profile Data")
        }
    }
}
