package com.neuropuzzle.app.engine.analyzer

class PatternDetector {
    fun detectPatterns(moves: List<String>): Map<String, Int> {
        val patterns = mutableMapOf<String, Int>()
        // Simple bigram pattern detection
        for (i in 0 until moves.size - 1) {
            val pattern = "-"
            patterns[pattern] = patterns.getOrDefault(pattern, 0) + 1
        }
        return patterns
    }
}
