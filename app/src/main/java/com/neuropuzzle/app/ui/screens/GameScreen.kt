package com.neuropuzzle.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neuropuzzle.app.data.models.Puzzle
import com.neuropuzzle.app.ui.components.PuzzleGrid
import com.neuropuzzle.app.ui.components.TimerBar
import com.neuropuzzle.app.ui.components.ActionButtons
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    puzzle: Puzzle,
    playerLevel: Int = 1,
    timeLimitSeconds: Int = 60,
    onGameFinished: (List<Pair<Int, Int>>, Long, Int) -> Unit,
    onGameOver: () -> Unit,
    onTrapHit: () -> Unit
) {
    var currentPath by remember { mutableStateOf(listOf(Pair(0, 0))) }
    var timeLeft by remember { mutableIntStateOf(timeLimitSeconds) }
    var mistakes by remember { mutableIntStateOf(0) }
    var startTime by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var showHint by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        } else {
            onGameOver()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Neural Level $playerLevel",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Complexity: ${puzzle.gridSize}x${puzzle.gridSize}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        val progress = timeLeft.toFloat() / timeLimitSeconds.toFloat()
        val minutes = timeLeft / 60
        val seconds = timeLeft % 60
        TimerBar(
            progress = progress,
            timeText = "%02d:%02d".format(minutes, seconds)
        )

        Box(modifier = Modifier.weight(1f)) {
            PuzzleGrid(
                puzzle = puzzle,
                currentPath = currentPath
            ) { x, y ->
                val pos = Pair(x, y)
                val last = currentPath.last()

                if (Math.abs(last.first - x) + Math.abs(last.second - y) == 1) {
                    if (puzzle.traps.contains(pos)) {
                        mistakes += 1
                        onTrapHit()
                        currentPath = listOf(Pair(0, 0)) // Reset on trap
                    } else {
                        currentPath = currentPath + pos
                        if (x == puzzle.gridSize - 1 && y == puzzle.gridSize - 1) {
                            val solveTime = System.currentTimeMillis() - startTime
                            onGameFinished(currentPath, solveTime, mistakes)
                        }
                    }
                }
            }
        }

        ActionButtons(
            onReset = {
                currentPath = listOf(Pair(0, 0))
                mistakes = 0
                startTime = System.currentTimeMillis()
            },
            onHint = { showHint = true }
        )

        if (showHint) {
            val nextStep = puzzle.solutionPath.getOrNull(currentPath.size)
            if (nextStep != null) {
                Text(
                    text = "Hint: Move to ${nextStep.first}, ${nextStep.second}",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            LaunchedEffect(showHint) {
                delay(2000L)
                showHint = false
            }
        }
    }
}
