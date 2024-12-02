package aockt.y2018

import io.github.jadarma.aockt.core.Solution
import java.math.BigInteger
import java.security.MessageDigest

object Y2018D02 : Solution {

    private fun findCheckSum(input: String): Int {
        var linesWithTwo = 0;
        var linesWithThree = 0;
        input.lines().forEach { line ->
            val charCountMap = mutableMapOf<Char, Int>()
            for (char in line.trim()) {
                charCountMap[char] = charCountMap.getOrDefault(char, 0) + 1
            }
            if (charCountMap.containsValue(2)) {
//                println("Line $line has a count of 2 found")
//                println("Map ${charCountMap.keys} + ${charCountMap.values} ")
                 linesWithTwo++
            }
            if (charCountMap.filter { it.value == 3 }.isNotEmpty()) {
//                println("Line $line has a count of 3 found")
                linesWithThree++
            }
        }
        println("Total Count of Two: $linesWithTwo, Three: $linesWithThree, Total: ${linesWithTwo * linesWithThree}")
        return linesWithTwo * linesWithThree
    }

    private fun findCommonCharsOfMatchingIds(input: String): String {
        var pair = Pair(0, 1)
        var minDiff = Int.MAX_VALUE
        var finalCommonChars = ""
        val words = input.lines().map { it }.toList()
//        println(words)
        for (i in 0 until words.size - 1) {
//            println(words[i])
            for (j in i + 1 until words.size - 1) {
//                println("Comparing ${words[i]} and ${words[j]}")
                var uncommonChars = 0;
                var index = 0
                var tempCommonChars = ""
                while (index < words[i].length) {
//                    println("Comparing ${words[i][index]} to ${words[j][index]}")
                    if (words[i][index] != words[j][index]) {
                        uncommonChars += 1
                    } else {
                        tempCommonChars += words[i][index]
                    }
                    index++
                }
                if (uncommonChars < minDiff) {
                    minDiff = uncommonChars
                    pair = Pair(i, j)
                    finalCommonChars = tempCommonChars
                    println("Updating to new pair for $i to $j with $uncommonChars and common chars $finalCommonChars")
                }
            }

        }
        return finalCommonChars
    }



    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }


    override fun partOne(input: String): Int = findCheckSum(input)
    override fun partTwo(input: String): String = findCommonCharsOfMatchingIds(input)

}