package com.neuropuzzle.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatsCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = label)
            Text(text = value)
        }
    }
}
