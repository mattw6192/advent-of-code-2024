package aockt.y2024

import aockt.y9999.Y9999D01.parseInput
import io.github.jadarma.aockt.core.Solution

object Y2024D05 : Solution {

    private var instructions = listOf(listOf<Int>())
    private var pageList = listOf(listOf<Int>())

    private fun parseInput(section: String, splitOn: String): List<List<Int>> {
        return section.split("\n").map { line ->
            line.split(splitOn).map { it.toInt() }
        }
    }

    private fun orderSearch(input: String, part: Int): Int {
        instructions = parseInput(input.lines().first(), "|")
        pageList = parseInput(input.lines().last(), "|")

//            Regex("""(\d+)\|(\d+)""")
//            .findAll(input)
//            .groupBy({ it.groupValues[1] }, { it.groupValues[2] })

        val pagesToCheck = Regex("""\d+(,\d+)+""")
            .findAll(input)
            .map { it.groupValues[0].split(",") }
            .toList()

        if (part == 1) {
            return pagesToCheck.filter { isValidUpdate(it, rules) }.sumOf { it[it.count() / 2].toInt() }
        } else {
            return pagesToCheck
                .filter { !isValidUpdate(it, rules) }
                .sumOf() { correctUpdate(it, rules)
                    .let { it[it.count() / 2].toInt() } }
        }

    }

    private fun correctUpdate(update: List<String>, rules: Map<String, List<String>>): List<String> {
        var correctedUpdate = update.toMutableList()

        while (!isValidUpdate(correctedUpdate, rules)) {
            run breaking@{
                correctedUpdate.withIndex().forEach{ (index, page) ->
                    val previousPages = correctedUpdate.take(index)
                    val shouldBeLaterPages = rules[page] ?: listOf()
                    val firstPageToSwapIndex = previousPages.indexOfFirst { shouldBeLaterPages.contains(it)}
                    if (firstPageToSwapIndex != -1) {
                        val firstPageToSwap = previousPages[firstPageToSwapIndex]
                        correctedUpdate[index] = firstPageToSwap
                        correctedUpdate[firstPageToSwapIndex] = page
                        return@breaking
                    }
                }
            }
        }

        return correctedUpdate

    }


    private fun isValidUpdate(update: List<String>, orderingRules: Map<String, List<String>>) : Boolean {
        return update.withIndex().fold(listOf<Boolean>()) { a, (index, page) ->
            val previousPages = update.take(index)
            val shouldBeLaterPages = orderingRules[page] ?: listOf()
            if (previousPages.any { shouldBeLaterPages.contains(it)}) (a + true) else (a + false)
        }.all(Boolean::not)
    }


    override fun partOne(input: String): Int = orderSearch(input, 1)
    override fun partTwo(input: String): Int = orderSearch(input, 2)

}