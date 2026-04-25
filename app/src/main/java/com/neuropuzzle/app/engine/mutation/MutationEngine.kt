package com.neuropuzzle.app.engine.mutation

import com.neuropuzzle.app.data.models.PlayerProfile
import com.neuropuzzle.app.data.models.PuzzleDNA

class MutationEngine(private val ruleMutator: RuleMutator = RuleMutator()) {
    fun mutate(dna: PuzzleDNA, player: PlayerProfile): PuzzleDNA {
        val newDna = dna.copy(rules = dna.rules.toMutableList())

        if (player.avgSolveTime < 8) {
            newDna.complexity += 1
        }

        if (player.mistakeRate > 0.4) {
            newDna.deceptionLevel += 1
        }

        if (player.strategyType == "repetitive") {
            newDna.rules.shuffle()
        }

        if (player.adaptabilityScore > 0.7) {
            newDna.branchingFactor += 1
        }

        // Advanced rule mutation
        val updatedRules = ruleMutator.suggestNewRules(newDna.rules, player)
        newDna.rules.clear()
        newDna.rules.addAll(updatedRules)

        return newDna
    }
}
