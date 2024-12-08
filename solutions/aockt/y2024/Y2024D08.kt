package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import kotlin.math.abs
import kotlin.math.min

typealias Antenna = Pair<Int, Int>

object Y2024D08 : Solution {

    private fun solve(input: String, part: Int): Int {
        val width = input.lines().first().count()
        val height = input.lines().count()

        val antennae = input.lines()
            .withIndex()
            .flatMap { (i, line) ->
                line.toCharArray()
                    .withIndex()
                    .map { (j, char) -> if (char != '.') char to Antenna(i, j) else null}
            }
            .filterNotNull()
            .groupBy({ it.first }, { it.second })

        return if (part == 1) {
            antennae
                .values
                .flatMap { it.mapPairs { a, b -> getAntiNodes(a,b)}.flatten()}
                .toSet()
                .count {it.first in 0 until height && it.second in 0 until width }
        } else {
            antennae
                .values
                .flatMap { it.mapPairs { a, b -> getAntiNodesPart2(a,b,width, height)}.flatten()}
                .toSet()
                .count {it.first in 0 until height && it.second in 0 until width }
        }


    }

    private fun getAntiNodes(first: Antenna, second: Antenna): List<Antenna> {
        val offset = first - second
        return listOf(first + offset, second - offset)
    }

    private fun getAntiNodesPart2(first: Antenna, second: Antenna, width: Int, height: Int): List<Antenna> {
        val offset = first - second
        val maxVert = abs(height / offset.first)
        val maxHoriz = abs(width / offset.second)
        val min = min(maxVert, maxHoriz)
        return (0..min)
            .flatMap { listOf(first + offset * it, second - offset * it) }
            .filter { it.first in 0 until height && it.second in 0 until width}
    }


    override fun partOne(input: String): Int = solve(input, 1)
    override fun partTwo(input: String): Int = solve(input, 2)

    private fun <T, R> List<T>.mapPairs(transform: (T, T) -> R): List<R> {
        return this.flatMapIndexed { i, a ->
            this.drop(i + 1).map { b ->
                transform(a, b)
            }
        }
    }
    operator fun Antenna.plus(other: Antenna): Antenna = Antenna(first + other.first, second + other.second)
    operator fun Antenna.minus(other: Antenna): Antenna = Antenna(first - other.first, second - other.second)
    operator fun Antenna.times(other: Int): Antenna = Antenna(first * other, second * other)

}