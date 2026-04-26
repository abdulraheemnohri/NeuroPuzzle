package com.neuropuzzle.app.engine.mutation

import com.neuropuzzle.app.data.models.PlayerProfile
import com.neuropuzzle.app.data.models.PuzzleDNA

class MutationEngine(private val ruleMutator: RuleMutator = RuleMutator()) {
    fun mutate(dna: PuzzleDNA, player: PlayerProfile): PuzzleDNA {
        val newDna = dna.copy(rules = dna.rules.toMutableList())

        // Skill-based scaling
        val skillMultiplier = (player.skillLevel / 2).coerceAtLeast(1)

        if (player.avgSolveTime < 8) {
            newDna.complexity += 1 * skillMultiplier
        }

        if (player.mistakeRate > 0.4) {
            newDna.deceptionLevel += 1 * skillMultiplier
        }

        if (player.strategyType == "repetitive") {
            newDna.rules.shuffle()
        }

        if (player.adaptabilityScore > 0.7) {
            newDna.branchingFactor += 1
        }

        val updatedRules = ruleMutator.suggestNewRules(newDna.rules, player)
        newDna.rules.clear()
        newDna.rules.addAll(updatedRules)

        return newDna
    }
}
