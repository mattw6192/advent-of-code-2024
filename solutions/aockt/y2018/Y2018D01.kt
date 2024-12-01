package aockt.y2018

import io.github.jadarma.aockt.core.Solution

object Y2018D01 : Solution {

    private fun parseInputToList(input: String): List<Int> {
        if (input.contains(", ")) {
            return input.trim().split(", ").map { it.toInt() }.toList()
        }
        return input.trim().replace("+", "").lines().map(String::toInt)
    }

    private fun determineFrequency(input: List<Int>): Int {
        var frequency = 0
        for (i in input) {
            frequency += i
        }

        return frequency
    }

    private fun findFirstDuplicateFrequency(input: List<Int>): Int {
        val emptyMap = mutableMapOf<Int, Int>()
        var frequency = 0

        emptyMap[0] = 1

        while (true) {
            for (i in input) {
                frequency += i
                if (emptyMap.containsKey(frequency)) {
                    return frequency
                } else {
                    emptyMap[frequency] = 1
                }
            }
        }

    }


    override fun partOne(input: String): Int = determineFrequency(parseInputToList(input))
    override fun partTwo(input: String): Int = findFirstDuplicateFrequency(parseInputToList(input))

}