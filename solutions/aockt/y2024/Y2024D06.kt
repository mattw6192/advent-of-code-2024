package aockt.y2024

import io.github.jadarma.aockt.core.Solution

object Y2024D06 : Solution {

    private var matrix = listOf(listOf<Char>())
    private var currentPosition = Pair(0, 0)
    private var currentDirection = "UP"
    private var visited = mutableSetOf<Pair<Int, Int>>()

    private fun parseInput(input: String) {
        val matrixList = mutableListOf<List<Char>>()
        input.lines().forEachIndexed { row, line ->
            val rowList = mutableListOf<Char>()
            line.toCharArray().forEachIndexed { col, c ->
                rowList.add(c)
                when (c) {
                    '^' -> {
                        currentPosition = Pair(row, col)
                        currentDirection = "UP"
                    }
                }
            }
            matrixList.add(rowList)
        }
        matrix = matrixList
        visited = mutableSetOf(currentPosition)
    }


    private fun updateDirectionViaRightTurn() {
        if (currentDirection == "UP") {
            currentDirection = "RIGHT"
        } else if (currentDirection == "RIGHT") {
            currentDirection = "DOWN"
        } else if (currentDirection == "LEFT") {
            currentDirection = "UP"
        } else if (currentDirection == "DOWN") {
            currentDirection = "LEFT"
        }
    }

    private fun calculateNextPosition(): Pair<Int, Int> {
        when (currentDirection) {
            "UP" -> {
                return Pair(currentPosition.first - 1, currentPosition.second)
            }
            "RIGHT" -> {
                return Pair(currentPosition.first, currentPosition.second + 1)
            }
            "LEFT" -> {
                return Pair(currentPosition.first, currentPosition.second - 1)
            }
            "DOWN" -> {
                return Pair(currentPosition.first + 1, currentPosition.second)
            }
        }
        return currentPosition
    }

    private fun orderSearch(input: String): Int {
        parseInput(input)
        println("startLocation $currentPosition")
        println("matrix size: ${matrix.size}, width: ${matrix[0].size}")

        var nextPosition = calculateNextPosition()

        while (nextPosition.first >= 0 && nextPosition.first < matrix.size && nextPosition.second >= 0 && nextPosition.second < matrix[0].size) {

            if (matrix[nextPosition.first][nextPosition.second] == '#') {
                updateDirectionViaRightTurn()
            } else {
                moveForward(nextPosition)
            }
            nextPosition = calculateNextPosition()
        }

        return visited.size
    }


    private fun part2(input: String): Int {
        val grid: List<CharArray> = input.lines().map { it.toCharArray() }

        val start = grid.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == '^') Point2D(x,y) else null
            }
        }.filterNotNull().first()

        return traverse(grid, start)
            .first
            .filterNot { it == start }
            .count { candidate ->
                grid[candidate] = '#'
                traverse(grid, start).also { grid[candidate] = '.'}.second
            }
    }

    private fun traverse(grid: List<CharArray>, start: Point2D): Pair<Set<Point2D>, Boolean> {
        val seen = mutableSetOf<Pair<Point2D, Point2D>>()
        var location = start
        var direction = Point2D.NORTH

        while (grid[location] != null && (location to direction) !in seen) {
            seen += location to direction
            val next = location + direction

            if(grid[next] == '#') direction = direction.turn()
            else location = next
        }
        return seen.map { it.first }.toSet() to (grid[location] != null)
    }

    private operator fun List<CharArray>.get(at: Point2D): Char? =
        getOrNull(at.y)?.getOrNull(at.x)

    private operator fun List<CharArray>.set(at: Point2D, c: Char) {
        this[at.y][at.x] = c
    }

    private fun Point2D.turn(): Point2D =
        when (this) {
            Point2D.NORTH -> Point2D.EAST
            Point2D.EAST -> Point2D.SOUTH
            Point2D.SOUTH -> Point2D.WEST
            Point2D.WEST -> Point2D.NORTH
            else -> throw IllegalStateException("Invalid direction $this")
        }


    private fun moveForward(nextPosition: Pair<Int, Int>) {
        if (!visited.contains(nextPosition)) {
            visited.add(nextPosition)
        }
        currentPosition = nextPosition
        println("Visiting: $nextPosition")
    }

    override fun partOne(input: String): Int = orderSearch(input)
    override fun partTwo(input: String): Int = part2(input)

    data class Point2D(val x: Int, val y: Int) {
        operator fun plus(other: Point2D): Point2D = Point2D(x + other.x, y + other.y)

        companion object {
            val NORTH = Point2D(0, -1)
            val EAST = Point2D(1, 0)
            val SOUTH = Point2D(0, 1)
            val WEST = Point2D(-1, 0)
        }
    }
}