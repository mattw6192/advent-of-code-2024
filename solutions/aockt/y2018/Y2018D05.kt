package aockt.y2018

import io.github.jadarma.aockt.core.Solution
import java.lang.Integer.min

object Y2018D05 : Solution {

    private fun performPolymerReactions(input: String): Int {
        var polymer = input.toMutableList()

        var firstIndex = 0
        var secondIndex = 1

        while (secondIndex <= polymer.lastIndex) {
            var firstChar = polymer[firstIndex]
            var secondChar = polymer[secondIndex]
//            println("Comparing $firstChar and $secondChar")
            if (firstChar.uppercaseChar() == secondChar.uppercaseChar() && firstChar != secondChar) {
//                println("Found reaction for $firstChar:$firstIndex and $secondChar:$secondIndex")
//                println("Old Polymer $polymer")

                polymer.removeAt(firstIndex)
                polymer.removeAt(firstIndex)
//                println("New Polymer $polymer")
                if (firstIndex >= 2) {
                    firstIndex -= 2
                    secondIndex -= 2
                } else {
                    firstIndex = 0
                    secondIndex = 1
                }

            }
            firstIndex++
            secondIndex++
        }

        println("Final Polymer $polymer")
        return polymer.size
    }

    private fun performPolymerReactionsPart2(input: String): Int {
        var chars = mutableSetOf<Char>()
        for (char in input) {
            chars.add(char.uppercaseChar())
        }

        println("Chars $chars")

        var min: Int = 999999
        for (char in chars) {
            var newString = input.toMutableList().filter { it.uppercaseChar() != char }.joinToString("")
            println("Testing $newString")

            val resp = performPolymerReactions(newString)
            println("Testing $newString returned $resp")
            min = min(min, resp)
        }

        return min
    }


    override fun partOne(input: String): Int = performPolymerReactions(input)
    override fun partTwo(input: String): Int = performPolymerReactionsPart2(input)

}