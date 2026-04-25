package com.neuropuzzle.app.engine.core

import com.neuropuzzle.app.data.models.Puzzle

class PuzzleEvaluator {
    fun isSolutionCorrect(puzzle: Puzzle, path: List<Pair<Int, Int>>): Boolean {
        if (path.isEmpty()) return false
        if (path.last() != Pair(puzzle.gridSize - 1, puzzle.gridSize - 1)) return false

        // Basic check: all moves are adjacent
        for (i in 0 until path.size - 1) {
            val p1 = path[i]
            val p2 = path[i+1]
            val dist = Math.abs(p1.first - p2.first) + Math.abs(p1.second - p2.second)
            if (dist != 1) return false
            if (puzzle.traps.contains(p2)) return false
        }
        return true
    }
}
