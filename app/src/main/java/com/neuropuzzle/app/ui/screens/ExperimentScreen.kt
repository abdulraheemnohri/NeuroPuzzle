package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neuropuzzle.app.data.models.PuzzleDNA

@Composable
fun ExperimentScreen(onStartWithDNA: (PuzzleDNA) -> Unit) {
    var complexity by remember { mutableFloatStateOf(1f) }
    var deception by remember { mutableFloatStateOf(1f) }
    var branching by remember { mutableFloatStateOf(1f) }
    var timePressure by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Experiment Mode", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Text("Complexity: ${complexity.toInt()}")
        Slider(value = complexity, onValueChange = { complexity = it }, valueRange = 1f..10f)

        Text("Deception: ${deception.toInt()}")
        Slider(value = deception, onValueChange = { deception = it }, valueRange = 1f..10f)

        Text("Branching: ${branching.toInt()}")
        Slider(value = branching, onValueChange = { branching = it }, valueRange = 1f..5f)

        Row(modifier = Modifier.padding(vertical = 16.dp)) {
            Text("Time Pressure")
            Checkbox(checked = timePressure, onCheckedChange = { timePressure = it })
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                onStartWithDNA(
                    PuzzleDNA(
                        complexity = complexity.toInt(),
                        deceptionLevel = deception.toInt(),
                        branchingFactor = branching.toInt(),
                        timePressure = timePressure,
                        rules = mutableListOf()
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Initialize DNA")
        }
    }
}
