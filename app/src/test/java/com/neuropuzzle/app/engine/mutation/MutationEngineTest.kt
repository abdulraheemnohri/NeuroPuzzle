package com.neuropuzzle.app.engine.mutation

import com.neuropuzzle.app.data.models.PlayerProfile
import com.neuropuzzle.app.data.models.PuzzleDNA
import org.junit.Assert.assertEquals
import org.junit.Test

class MutationEngineTest {
    @Test
    fun testComplexityIncrease() {
        val engine = MutationEngine()
        val dna = PuzzleDNA(complexity = 1, deceptionLevel = 1, branchingFactor = 1, timePressure = false, rules = mutableListOf())
        val player = PlayerProfile(avgSolveTime = 5f) // Fast solve time

        val mutated = engine.mutate(dna, player)
        assertEquals(2, mutated.complexity)
    }

    @Test
    fun testDeceptionIncrease() {
        val engine = MutationEngine()
        val dna = PuzzleDNA(complexity = 1, deceptionLevel = 1, branchingFactor = 1, timePressure = false, rules = mutableListOf())
        val player = PlayerProfile(mistakeRate = 0.5f) // High mistake rate

        val mutated = engine.mutate(dna, player)
        assertEquals(2, mutated.deceptionLevel)
    }
}
