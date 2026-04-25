package com.neuropuzzle.app.engine.core

import com.neuropuzzle.app.data.models.GameStats
import com.neuropuzzle.app.data.models.PlayerProfile
import com.neuropuzzle.app.data.models.Puzzle
import com.neuropuzzle.app.data.models.PuzzleDNA
import com.neuropuzzle.app.data.storage.PuzzleDao
import com.neuropuzzle.app.engine.analyzer.PlayerAnalyzer
import com.neuropuzzle.app.engine.generator.PuzzleGenerator
import com.neuropuzzle.app.engine.mutation.MutationEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameManager(
    private val generator: PuzzleGenerator,
    private val analyzer: PlayerAnalyzer,
    private val mutationEngine: MutationEngine,
    private val dao: PuzzleDao
) {
    private var currentDNA: PuzzleDNA? = null
    private val _playerProfile = MutableStateFlow(PlayerProfile())
    val playerProfile: StateFlow<PlayerProfile> = _playerProfile

    suspend fun loadProfile() {
        val profile = dao.getPlayerProfile()
        if (profile != null) {
            _playerProfile.value = profile
        }
    }

    fun startNewGame(dna: PuzzleDNA): Puzzle {
        currentDNA = dna
        return generator.generatePuzzle(dna)
    }

    suspend fun onGameFinished(puzzleId: String, moves: List<String>, solveTime: Long, mistakes: Int) {
        val strategy = analyzer.analyze(moves)
        val currentProfile = _playerProfile.value

        // Update stats
        currentProfile.strategyType = strategy
        currentProfile.avgSolveTime = (currentProfile.avgSolveTime + solveTime) / 2f
        currentProfile.mistakeRate = (currentProfile.mistakeRate + (mistakes.toFloat() / moves.size.coerceAtLeast(1))) / 2f

        // XP and Leveling
        val xpGained = calculateXP(solveTime, mistakes)
        currentProfile.experiencePoints += xpGained

        val xpNeeded = currentProfile.currentLevel * 100
        if (currentProfile.experiencePoints >= xpNeeded) {
            currentProfile.currentLevel += 1
            currentProfile.experiencePoints -= xpNeeded
            currentProfile.skillLevel += 1 // Influence difficulty
        }

        // Save to DB
        dao.savePlayerProfile(currentProfile)
        dao.insertGameStats(GameStats(puzzleId = puzzleId, solveTime = solveTime, mistakes = mistakes))

        // Mutate DNA
        currentDNA?.let {
            currentDNA = mutationEngine.mutate(it, currentProfile)
        }

        _playerProfile.value = currentProfile
    }

    private fun calculateXP(solveTime: Long, mistakes: Int): Int {
        // Base XP: 50
        // Speed bonus: up to 50
        // Mistake penalty: -10 per mistake
        val speedBonus = (30000 - solveTime).coerceAtLeast(0) / 600
        return (50 + speedBonus.toInt() - (mistakes * 10)).coerceAtLeast(10)
    }

    fun getCurrentDNA(): PuzzleDNA? = currentDNA
}
