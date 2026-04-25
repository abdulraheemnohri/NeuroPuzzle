package com.neuropuzzle.app.engine.mutation

import com.neuropuzzle.app.data.models.PlayerProfile

class RuleMutator {
    private val availableRules = listOf(
        "no_backtrack",
        "time_limit",
        "hidden_traps",
        "gravity_shift"
    )

    fun suggestNewRules(currentRules: List<String>, player: PlayerProfile): List<String> {
        val newRules = currentRules.toMutableList()

        if (player.skillLevel > 3 && !newRules.contains("hidden_traps")) {
            newRules.add("hidden_traps")
        }

        if (player.avgSolveTime < 10 && !newRules.contains("time_limit")) {
            newRules.add("time_limit")
        }

        if (player.strategyType == "bruteforce" && !newRules.any { it.startsWith("limit_") }) {
            newRules.add("limit_20")
        }

        return newRules.distinct()
    }
}
