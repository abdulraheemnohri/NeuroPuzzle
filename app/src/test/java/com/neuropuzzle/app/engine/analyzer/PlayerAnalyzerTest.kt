package com.neuropuzzle.app.engine.analyzer

import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerAnalyzerTest {
    @Test
    fun testRepetitiveStrategy() {
        val analyzer = PlayerAnalyzer()
        val moves = listOf("0,0", "0,1", "0,0", "0,1", "0,0", "0,1")
        assertEquals("repetitive", analyzer.analyze(moves))
    }

    @Test
    fun testBruteforceStrategy() {
        val analyzer = PlayerAnalyzer()
        val moves = (1..25).map { "$it,$it" }
        assertEquals("bruteforce", analyzer.analyze(moves))
    }

    @Test
    fun testLogicalStrategy() {
        val analyzer = PlayerAnalyzer()
        val moves = listOf("0,0", "0,1", "1,1", "1,2", "2,2")
        assertEquals("logical", analyzer.analyze(moves))
    }
}
