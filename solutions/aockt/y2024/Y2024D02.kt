package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import kotlin.math.abs

object Y2024D02 : Solution {

    private fun List<Int>.isSafe():Boolean {
        val differences = windowed(2).map { it[1] - it[0] }
        return differences.all { it in 1..3 } || differences.all {it < 0 && it >= -3}
    }

    private fun List<Int>.isSafeWithDampener():Boolean {
        if (isSafe()) return true
        for (index in indices) {
            // remove current index and try again
            val withoutIndex = filterIndexed { i, _ -> i != index }
            if (withoutIndex.isSafe()) return true
        }
        return false
    }

    private fun determineSafeLevels(input: String, part: Int): Int {
        var safeLevels = 0
        input.lines().forEach { line ->
            val splitLine = line.trimStart().trimEnd().split(" ").filter { it != "" }.map{ it.toInt() }
            if (part == 1 && splitLine.isSafe()) {
                safeLevels++
                println("Level $line is safe")
            }
            else if (part == 2 && splitLine.isSafeWithDampener()) {
                safeLevels++
                println("Level $line is safe")
            }
        }
        return safeLevels

    }

    override fun partOne(input: String): Int = determineSafeLevels(input, 1)
    override fun partTwo(input: String): Int = determineSafeLevels(input, 2)

}