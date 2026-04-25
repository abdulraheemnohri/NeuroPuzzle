package com.neuropuzzle.app.engine.core

import com.neuropuzzle.app.data.models.PlayerProfile
import com.neuropuzzle.app.data.models.Puzzle
import com.neuropuzzle.app.data.models.PuzzleDNA
import com.neuropuzzle.app.engine.analyzer.PlayerAnalyzer
import com.neuropuzzle.app.engine.generator.PuzzleGenerator
import com.neuropuzzle.app.engine.mutation.MutationEngine

class GameManager(
    private val generator: PuzzleGenerator,
    private val analyzer: PlayerAnalyzer,
    private val mutationEngine: MutationEngine
) {
    private var currentDNA: PuzzleDNA? = null
    private var playerProfile: PlayerProfile = PlayerProfile()

    fun startNewGame(dna: PuzzleDNA): Puzzle {
        currentDNA = dna
        return generator.generatePuzzle(dna)
    }

    fun onGameFinished(moves: List<String>, solveTime: Long, mistakes: Int) {
        val strategy = analyzer.analyze(moves)
        playerProfile.strategyType = strategy
        playerProfile.avgSolveTime = (playerProfile.avgSolveTime + solveTime) / 2f
        playerProfile.mistakeRate = (playerProfile.mistakeRate + (mistakes.toFloat() / moves.size.coerceAtLeast(1))) / 2f

        currentDNA?.let {
            currentDNA = mutationEngine.mutate(it, playerProfile)
        }
    }

    fun getCurrentDNA(): PuzzleDNA? = currentDNA
    fun getPlayerProfile(): PlayerProfile = playerProfile
}
