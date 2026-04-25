package com.neuropuzzle.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.neuropuzzle.app.data.models.Puzzle

@Composable
fun PuzzleGrid(
    puzzle: Puzzle,
    currentPath: List<Pair<Int, Int>>,
    onCellClick: (Int, Int) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val size = puzzle.gridSize
        LazyVerticalGrid(
            columns = GridCells.Fixed(size),
            modifier = Modifier.padding(16.dp)
        ) {
            items(size * size) { index ->
                val x = index % size
                val y = index / size
                val pos = Pair(x, y)
                val isTrap = puzzle.traps.contains(pos)
                val isPath = currentPath.contains(pos)
                val isGoal = x == size - 1 && y == size - 1

                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(2.dp)
                        .background(
                            when {
                                isGoal -> Color.Green
                                isPath -> Color.Blue
                                // isTrap -> Color.Red // Maybe hide traps in some modes
                                else -> Color.LightGray
                            }
                        )
                        .clickable { onCellClick(x, y) },
                    contentAlignment = Alignment.Center
                ) {
                    if (isGoal) Text("G")
                }
            }
        }
    }
}
