package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.PriorityQueue

object Y2024D09 : Solution {

    private fun solve(input: String): String {
        var memory = mutableListOf<String>()
        var currentDigit = 0
        var stage = "memory"
        for (char in input.toCharArray()) {
            for (i in 0 until char.digitToInt()) {
                if (stage == "memory") {
                    memory.add(currentDigit.toString())
                } else {
                    memory.add(".")

                }
            }
            if (stage == "memory") {
                currentDigit++
                stage = "blank"
            } else {
                stage = "memory"
            }
        }

//        println(memory.joinToString(""))

        val defragResult = defragmentMemory(memory)

        return defragResult


    }

    private fun defragmentMemory(memory: MutableList<String>): String {
        println("Start defragment")
        var left = 0
        var right = memory.size - 1
        while (left != right) {
            if (memory[left] != ".") {
                left++
            }
            if (memory[right] == ".") {
                right--
            }
            if (memory[left] == "." && memory[right] != ".") { // perform swap
                memory[left] = memory[right]
                memory[right] = "."
                right--
                left++
//                println("Updated Memory: ${memory.joinToString("")}")
            }
        }
        return calculateCheckSum(memory)
    }

    private fun calculateCheckSum(memory: MutableList<String>): String {
        println("Calc Checksum")
        var checksum: Long = 0
        for (i in memory.indices) {
            if (memory[i] == ".") continue
            checksum += memory[i].toLong() * i
        }
        return checksum.toString()
    }

    private fun solvePart2(input: String): String {
        val allBlocks = findAllMemoryBlocks(input)
        val freeSpace: MutableMap<Int, PriorityQueue<Int>> = allBlocks
            .filter { it.fileId == null }
            .groupBy({ it.length }, { it.start })
            .mapValues { (_, v) -> PriorityQueue(v) }
            .toMutableMap()

        return allBlocks.filterNot { it.fileId == null }.reversed().sumOf { block ->
            block.checksum(
                freeSpace.findSpace(block)
            )
        }.toString()
    }

    private fun findAllMemoryBlocks(input: String): List<Block>  {
        val list = input
            .windowed(2, 2, true)
            .withIndex()
            .flatMap { (index, value) ->
                List(value.first().digitToInt()) { _ -> index.toLong() } +
                        List(value.getOrElse(1){ _ -> '0' }.digitToInt()) { null }
            }

        return buildList(list)

    }

    private fun buildList(disk: List<Long?>): List<Block> = buildList {
        var blockStart = -1
        var previousValue: Long? = -1L
        disk.withIndex().forEach { (index, value) ->
            if (previousValue == -1L) {
                blockStart = index
                previousValue = value
            } else if (previousValue != value) {
                add(Block(blockStart, index - blockStart, previousValue))
                blockStart = index
                previousValue = value
            }
        }
        if (blockStart != -1) {
            add(Block(blockStart, disk.size - blockStart, previousValue))
        }
    }

    private fun MutableMap<Int, PriorityQueue<Int>>.findSpace(block: Block): Int =
        (block.length..9).mapNotNull { trySize ->
            if (this[trySize]?.isNotEmpty() == true) trySize to this.getValue(trySize).first()
            else null
        }.sortedBy { it.second }.filter { it.second < block.start }.firstNotNullOfOrNull { (blockSize, startAt) ->
            this[blockSize]?.poll()
            if (blockSize != block.length) {
                val diff = blockSize - block.length
                computeIfAbsent(diff) { _ -> PriorityQueue() }.add(startAt + block.length)
            }
            startAt
        } ?: block.start



    override fun partOne(input: String): String = solve(input)
    override fun partTwo(input: String): String = solvePart2(input)

    private data class Block(val start: Int, val length: Int, val fileId: Long? = null) {
        fun checksum(index: Int = start): Long =
            (0..<length).sumOf {
                (index + it) * (fileId ?: 0L)
            }
    }

}