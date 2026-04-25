package com.neuropuzzle.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionButtons(onReset: () -> Unit, onHint: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onReset) {
            Text("Reset")
        }
        Button(onClick = onHint) {
            Text("Hint")
        }
    }
}
