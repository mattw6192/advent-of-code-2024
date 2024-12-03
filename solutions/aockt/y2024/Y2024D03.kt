package aockt.y2024

import io.github.jadarma.aockt.core.Solution

object Y2024D03 : Solution {

    private fun findCorruptedMemory(input: String): Int {
        var corruptedMemorySum = 0
        val regex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)")
        val matches = regex.findAll(input)
        val instructions = matches.map { it.groupValues[0] }.toList()
        println(instructions)
        instructions.forEach {i ->
            corruptedMemorySum += i.replace("mul(", "").replace(")", "").split(",").map { it.toInt() }.let { it[0] * it[1]}
        }
        println(corruptedMemorySum)
        return corruptedMemorySum

    }

    private fun findCorruptedMemoryPart2(input: String): Int {
        var corruptedMemorySum = 0
        val regex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don\\'t\\(\\)")
        val matches = regex.findAll(input)
        val instructions = matches.map { it.groupValues[0] }.toList()
        println(instructions)
        var currentOperation = "do()"
        instructions.forEach {i ->
            if (i == "don't()") currentOperation = i
            else if (i == "do()") currentOperation = i
            else if (currentOperation == "do()") {
                corruptedMemorySum += i.replace("mul(", "").replace(")", "").split(",").map { it.toInt() }.let { it[0] * it[1]}
                println("Allowing operation: $i")
            } else {
                println("Skipping operation: $i")
            }
        }
        println(corruptedMemorySum)
        return corruptedMemorySum

    }

    override fun partOne(input: String): Int = findCorruptedMemory(input)
    override fun partTwo(input: String): Int = findCorruptedMemoryPart2(input)

}