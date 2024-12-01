package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import kotlin.math.abs

object Y2024D01 : Solution {

    private fun parseInputToLeftRightLists(input: String): List<List<Int>> {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()
        input.lines().forEach { line ->
//            println("problem line: $line")
            val splitLine = line.trimStart().trimEnd().split(" ").filter { it != "" }
            leftList.add(splitLine[0].toInt())
            rightList.add(splitLine[1].toInt())
        }
        leftList.sort()
        rightList.sort()
        println("LEFT list $leftList")
        println("RIGHT list $rightList")
        return mutableListOf(leftList, rightList)
    }

    private fun determineTotalDistance(inputLists : List<List<Int>>): Int {
        var frequency = 0
        val leftList = inputLists[0]
        val rightList = inputLists[1]


//        println("Sizes ${leftList.size} : ${rightList.size}")
        for (i in leftList.indices) {
            val leftVal = leftList[i]
            val rightVal = rightList[i]
            val diff = abs(rightVal - leftVal)
//            println("Difference between $leftVal and $rightVal: $diff")
            frequency += diff
        }

        return frequency
    }

    private fun parseInputToSingularUniqueList(input: String): List<Int> {
        val leftList = mutableListOf<Int>()
        input.lines().forEach { line ->
            val splitLine = line.trimStart().trimEnd().split(" ").filter { it != "" }
            leftList.add(splitLine[0].toInt())
        }
        return leftList
    }

    private fun parseInputToFrequencyMap(input: String): MutableMap<Int, Int> {
        val freqMap = mutableMapOf<Int, Int>()
        input.lines().forEach { line ->
            val splitLine = line.trimStart().trimEnd().split(" ").filter { it != "" }
            val tempVal = splitLine[1].toInt()
            if (freqMap.containsKey(tempVal)) {
                freqMap[tempVal] = freqMap[tempVal]!! + 1
            } else {
                freqMap[tempVal] = 1
            }
        }
        return freqMap
    }

    private fun determineSimilarityScore(input : String): Int {
        var similarityScore = 0
        val leftList = parseInputToSingularUniqueList(input)
        val freqMap = parseInputToFrequencyMap(input)

        for (num in leftList) {
            val frequency = freqMap.getOrDefault(num, 0)
            val score = num * frequency
            similarityScore += score
        }

        return similarityScore
    }


    override fun partOne(input: String): Int = determineTotalDistance(parseInputToLeftRightLists(input))
    override fun partTwo(input: String): Int = determineSimilarityScore(input)

}