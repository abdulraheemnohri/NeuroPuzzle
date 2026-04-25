package com.neuropuzzle.app.engine.generator

import com.neuropuzzle.app.data.models.PuzzleDNA

object PuzzleFactory {
    fun createDefaultDNA(): PuzzleDNA {
        return PuzzleDNA(
            complexity = 1,
            deceptionLevel = 1,
            branchingFactor = 1,
            timePressure = false,
            rules = mutableListOf("no_backtrack")
        )
    }
}
