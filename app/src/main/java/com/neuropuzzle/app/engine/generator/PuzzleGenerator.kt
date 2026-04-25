package com.neuropuzzle.app.engine.generator

import com.neuropuzzle.app.data.models.Puzzle
import com.neuropuzzle.app.data.models.PuzzleDNA
import java.util.UUID
import kotlin.random.Random

class PuzzleGenerator {
    fun generatePuzzle(dna: PuzzleDNA): Puzzle {
        val size = dna.complexity + 4
        val solutionPath = generatePath(size)
        val traps = generateTraps(size, dna.deceptionLevel, solutionPath)

        return Puzzle(
            id = UUID.randomUUID().toString(),
            gridSize = size,
            solutionPath = solutionPath,
            traps = traps,
            rules = dna.rules
        )
    }

    private fun generatePath(size: Int): List<Pair<Int, Int>> {
        val path = mutableListOf<Pair<Int, Int>>()
        var curr = Pair(0, 0)
        path.add(curr)

        while (curr.first < size - 1 || curr.second < size - 1) {
            val moveRight = Random.nextBoolean()
            if (moveRight && curr.first < size - 1) {
                curr = Pair(curr.first + 1, curr.second)
            } else if (curr.second < size - 1) {
                curr = Pair(curr.first, curr.second + 1)
            } else {
                curr = Pair(curr.first + 1, curr.second)
            }
            path.add(curr)
        }
        return path
    }

    private fun generateTraps(size: Int, deception: Int, path: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val traps = mutableListOf<Pair<Int, Int>>()
        val trapCount = deception * 2
        val pathSet = path.toSet()

        var count = 0
        while (count < trapCount) {
            val x = Random.nextInt(size)
            val y = Random.nextInt(size)
            val pos = Pair(x, y)
            if (pos !in pathSet && pos !in traps) {
                traps.add(pos)
                count++
            }
        }
        return traps
    }
}
