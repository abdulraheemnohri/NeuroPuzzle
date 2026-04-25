package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.neuropuzzle.app.data.models.Puzzle
import com.neuropuzzle.app.ui.components.PuzzleGrid
import com.neuropuzzle.app.ui.components.TimerBar
import com.neuropuzzle.app.ui.components.ActionButtons

@Composable
fun GameScreen(puzzle: Puzzle, onGameFinished: (List<Pair<Int, Int>>) -> Unit) {
    var currentPath by remember { mutableStateOf(listOf(Pair(0, 0))) }

    Column(modifier = Modifier.fillMaxSize()) {
        TimerBar(progress = 0.5f, timeText = "00:45")
        Box(modifier = Modifier.weight(1f)) {
            PuzzleGrid(puzzle = puzzle, currentPath = currentPath) { x, y ->
                // Simple logic: add to path if adjacent
                val last = currentPath.last()
                if (Math.abs(last.first - x) + Math.abs(last.second - y) == 1) {
                    currentPath = currentPath + Pair(x, y)
                    if (x == puzzle.gridSize - 1 && y == puzzle.gridSize - 1) {
                        onGameFinished(currentPath)
                    }
                }
            }
        }
        ActionButtons(onReset = { currentPath = listOf(Pair(0, 0)) }, onHint = {})
    }
}
