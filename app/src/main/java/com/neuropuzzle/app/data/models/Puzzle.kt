package com.neuropuzzle.app.data.models

import java.util.UUID

data class Puzzle(
    val id: String = UUID.randomUUID().toString(),
    val gridSize: Int,
    val solutionPath: List<Pair<Int, Int>>,
    val traps: List<Pair<Int, Int>>,
    val rules: List<String>
)
