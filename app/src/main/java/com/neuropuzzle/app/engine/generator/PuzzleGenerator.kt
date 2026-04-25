package com.neuropuzzle.app.engine.generator

import com.neuropuzzle.app.data.models.Puzzle
import com.neuropuzzle.app.data.models.PuzzleDNA
import java.util.UUID
import kotlin.random.Random

class PuzzleGenerator {
    fun generatePuzzle(dna: PuzzleDNA): Puzzle {
        val size = if (dna.rules.contains("chaos")) dna.complexity + Random.nextInt(5) + 4 else dna.complexity + 4
        val solutionPath = generatePath(size)
        val branches = generateBranches(size, dna.branchingFactor, solutionPath)
        val traps = generateTraps(size, dna.deceptionLevel, solutionPath + branches)

        return Puzzle(
            id = UUID.randomUUID().toString(),
            gridSize = size,
            solutionPath = solutionPath,
            traps = traps + branches, // Dead ends act like traps or just diversions
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

    private fun generateBranches(size: Int, branchingFactor: Int, mainPath: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val branches = mutableListOf<Pair<Int, Int>>()
        val pathSet = mainPath.toSet()

        repeat(branchingFactor * 2) {
            val startNode = mainPath.random()
            var curr = startNode
            repeat(3) { // Small dead-end branches
                val neighbors = listOf(
                    Pair(curr.first + 1, curr.second),
                    Pair(curr.first - 1, curr.second),
                    Pair(curr.first, curr.second + 1),
                    Pair(curr.first, curr.second - 1)
                ).filter { it.first in 0 until size && it.second in 0 until size && it !in pathSet && it !in branches }

                if (neighbors.isNotEmpty()) {
                    curr = neighbors.random()
                    branches.add(curr)
                }
            }
        }
        return branches
    }

    private fun generateTraps(size: Int, deception: Int, occupied: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val traps = mutableListOf<Pair<Int, Int>>()
        val trapCount = deception * 2
        val occupiedSet = occupied.toSet()

        var count = 0
        while (count < trapCount) {
            val x = Random.nextInt(size)
            val y = Random.nextInt(size)
            val pos = Pair(x, y)
            if (pos !in occupiedSet && pos !in traps) {
                traps.add(pos)
                count++
            }
        }
        return traps
    }
}
