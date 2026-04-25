package com.neuropuzzle.app.engine.analyzer

class PlayerAnalyzer {
    fun analyze(moves: List<String>): String {
        return when {
            moves.isEmpty() -> "unknown"
            moves.distinct().size < moves.size / 2 -> "repetitive"
            moves.size > 20 -> "bruteforce"
            else -> "logical"
        }
    }
}
