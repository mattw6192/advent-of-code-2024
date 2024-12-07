package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import kotlin.math.abs

object Y2024D07 : Solution {

    private val operations: List<String> =  listOf("+", "*")

    private fun repairBridge(input : String, part: Int): String {
        var totalCalibration: Long = 0
        input.lines().forEach { line ->
            val splitLine = line.split(": ")
            val testResult = splitLine[0].toLong()
            val values = splitLine[1].split(" ").filter { it != "" }.map { it.toLong() }.toList()
            println(" Testing: $testResult with values: $values")
            totalCalibration += testMatches(testResult, values, part)
        }


        return totalCalibration.toString()
    }

    private fun testMatches(testResult: Long, values : List<Long>, part: Int) : Long {
        if (values.size == 1) return if (values[0] == testResult) testResult else 0
        if (values[0] > testResult) return 0

        val tempList = values.toMutableList()
        val left = tempList.removeAt(0)
        val right = tempList.removeAt(0)

        tempList.add(0, left + right)
//        println("---- $left + $right ==? $testResult")
        if (testMatches(testResult, tempList, part) > 0) {
//            println("-------- Match found $left + $right ==? $testResult")
            return testResult
        }
        tempList.removeAt(0)

        tempList.add(0, left * right)
//        println("---- $left * $right ==? $testResult")
        if (testMatches(testResult, tempList, part) > 0) {
//            println("-------- Match found $left * $right ==? $testResult")
            return testResult
        }
        tempList.removeAt(0)

        if (part == 2) {
            tempList.add(0, "$left$right".toLong())
//            println("---- $left || $right ==? $testResult")
            if (testMatches(testResult, tempList, part) > 0) {
//                println("-------- Match found $left || $right ==? $testResult")
                return testResult
            }
            tempList.removeAt(0)
        }


        return 0
    }


    override fun partOne(input: String): String = repairBridge(input, 1)
    override fun partTwo(input: String): String = repairBridge(input, 2)

}