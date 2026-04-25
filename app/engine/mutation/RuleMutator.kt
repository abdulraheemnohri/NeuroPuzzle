package com.neuropuzzle.app.engine.mutation

class RuleMutator {
    private val availableRules = listOf(
        "no_backtrack",
        "time_limit",
        "limited_moves",
        "hidden_traps",
        "gravity_shift"
    )

    fun suggestNewRules(currentRules: List<String>): List<String> {
        return availableRules.filter { !currentRules.contains(it) }.shuffled().take(1)
    }
}
