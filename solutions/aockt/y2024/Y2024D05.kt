package aockt.y2024

import io.github.jadarma.aockt.core.Solution

object Y2024D05 : Solution {

    private var instructions = listOf(listOf<Int>())
    private var pageList = listOf(listOf<Int>())

    private fun parseInput(input: String) {
        val instructionsList = mutableListOf<List<Int>>()
        val pageListTemp = mutableListOf<List<Int>>()
        input.lines().forEach {line ->
            if (line.contains("|")) {
                instructionsList.add(line.split("|").map { it.toInt() }.toList())
            } else if (line.contains(",")) {
                pageListTemp.add(line.split(",").map { it.toInt() }.toList())
            }
        }
        instructions = instructionsList
        pageList = pageListTemp
    }

    private inline fun Boolean.ifTrue(fn: () -> Unit) = if (this) fn() else Unit

    private fun List<List<Int>>.pagesAfter(page: Int): List<Int> {
        return filter { it.first() == page }.map { it.last() }
    }

    private fun isValid(pages: List<Int>): Boolean {
        val seen = mutableSetOf<Int>()
        pages.forEach { currentPage ->
            instructions.pagesAfter(currentPage)
                .any { it in seen }
                .ifTrue { return false }
            seen.add(currentPage)
        }
        return true
    }

    private fun orderPage(pages: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        pages.forEach { currentPage ->
            val pagesAfterCurrent = instructions.pagesAfter(currentPage)
            val newLocation = result
                .indexOfFirst { it in pagesAfterCurrent }
                .let { if (it == -1) result.size else it }
            result.add(newLocation, currentPage)
        }
        return result
    }

    private fun orderSearch(input: String, part: Int): Int {
        parseInput(input)

        println("instructions $instructions")

        println("pagelist: $pageList")
        return if (part == 1) {
            pageList.filter { isValid(it) }.sumOf { it[it.count() / 2] }
        } else {
            pageList
                .filter { !isValid(it) }
                .map { orderPage(it) }
                .sumOf() { it[it.count() / 2] }
        }
    }

    override fun partOne(input: String): Int = orderSearch(input, 1)
    override fun partTwo(input: String): Int = orderSearch(input, 2)

}