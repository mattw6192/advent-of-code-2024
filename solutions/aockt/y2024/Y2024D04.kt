package aockt.y2024

import io.github.jadarma.aockt.core.Solution

object Y2024D04 : Solution {

    private var searchArr = listOf('X', 'M', 'A', 'S')
    private var searchListPart2 = mutableListOf('M', 'S')

    private fun wordSearch(input: String): Int {
        var wordsFound = 0

        val matrix = initMatrix(input)
//        printMatrix(matrix)

        for(row in matrix.indices) {
            for(col in 0 until matrix[row].size) {
                if (matrix[row][col] == searchArr[0]) {
                    wordsFound += searchAllDirections(matrix, row, col)
                }
            }
        }

        return wordsFound

    }

    private fun wordSearchPart2(input: String): Int {
        var wordsFound = 0

        val matrix = initMatrix(input)
//        printMatrix(matrix)

        for(row in matrix.indices) {
            for(col in 0 until matrix[row].size) {
                if (matrix[row][col] == 'A') {
                    wordsFound += searchForX(matrix, row, col)
                }
            }
        }

        return wordsFound

    }

    private fun searchForX(matrix: List<List<Char>>, row: Int, col: Int): Int {
        println("- Start left-up for $row, $col")
        val leftUp = searchPart2(matrix, row, col, -1, -1)
        println("- Start right-up for $row, $col")
        val rightUp = searchPart2(matrix, row, col, -1, 1)
        println("- Start left-down for $row, $col")
        val leftDown = searchPart2(matrix, row, col, 1, -1)
        println("- Start right-down for $row, $col")
        val rightDown = searchPart2(matrix, row, col, 1, 1)
        if (searchListPart2.contains(leftUp) && searchListPart2.contains(rightUp)
            && searchListPart2.contains(leftDown) && searchListPart2.contains(rightDown)
            && leftUp != rightDown && leftDown != rightUp) {
            println("--------------- XMAS CELEBRATION --------------- $row, $col , ${matrix[row][col]}")
            return 1
        } else return 0
    }

    // 5,2 wrong - only checking if in list, not if opposite remaining

    private fun searchPart2(matrix: List<List<Char>>, row: Int, col: Int, rowDir: Int, colDir: Int): Char {
        var newRow = row + rowDir
        var newCol = col + colDir
        return if (newRow >= 0 && newRow < matrix.size && newCol >= 0 && newCol < matrix[newRow].size) {
            println("----- Search $newRow, $newCol and found ${matrix[newRow][newCol]}")
            if (searchListPart2.contains(matrix[newRow][newCol])) {
                println("---------- Match Found")// found match
                matrix[newRow][newCol]
            } else {
                '0'
            }
        } else {
            '0'
        }
    }

    private fun searchAllDirections(matrix: List<List<Char>>, row: Int, col: Int): Int {
        var count = 0


        // left up
        println("- Start left-up for $row, $col")
        count += search(matrix, row, col, -1, -1, 1)
        // up
        println("- Start up for $row, $col")
        count += search(matrix, row, col, -1, 0, 1)
        // right up
        println("- Start right-up for $row, $col")
        count += search(matrix, row, col, -1, 1, 1)
        // left
        println("- Start left for $row, $col")
        count += search(matrix, row, col, 0, -1, 1)
        // right
        println("- Start right for $row, $col")
        count += search(matrix, row, col, 0, 1, 1)
        // left down
        println("- Start left-down for $row, $col")
        count += search(matrix, row, col, 1, -1, 1)
        // down
        println("- Start down for $row, $col")
        count += search(matrix, row, col, 1, 0, 1)
        // right down
        println("- Start right-down for $row, $col")
        count += search(matrix, row, col, 1, 1, 1)

        println("Final: $count found for $row, $col")
        return count
    }

    private fun search(matrix: List<List<Char>>, row: Int, col: Int, rowDir: Int, colDir: Int, searchIndex: Int): Int {
        var newRow = row + rowDir
        var newCol = col + colDir
        if (newRow >= 0 && newRow < matrix.size && newCol >= 0 && newCol < matrix[newRow].size) {
            println("----- Search $newRow, $newCol looking for ${searchArr[searchIndex]} and found ${matrix[newRow][newCol]}")
            if (matrix[newRow][newCol] == searchArr[searchIndex]) { // keep searching
//                println("found match at $newRow, $newCol for index $searchIndex")
                if (searchIndex == 3) {
                    println("---------- Match Found")
                    return 1
                } else {
                    return search(matrix, newRow, newCol, rowDir, colDir, searchIndex+1)
                }
            } else {
                return 0
            }
        } else {
            return 0
        }
    }

    private fun initMatrix(input: String): List<List<Char>> {
        val matrix = mutableListOf<List<Char>>()

        input.lines().forEach { word ->
            val line = word.toCharArray().toList()
            matrix.add(line)
        }
        return matrix
    }

    private fun printMatrix(matrix: List<List<Char>>) {
        for (row in matrix) {
            for (col in row) {
                print(col)
            }
            println()
        }
        println()
    }


    override fun partOne(input: String): Int = wordSearch(input)
    override fun partTwo(input: String): Int = wordSearchPart2(input)

}