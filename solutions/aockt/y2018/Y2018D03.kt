package aockt.y2018

import io.github.jadarma.aockt.core.Solution
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Y2018D03 : Solution {

    private fun findOverlappingArea(input: String): Int {
        var objects = readInputToClaimsList(input)
        var usedPoints = mutableMapOf<Pair<Int, Int>, Int>()
        objects.forEach {obj ->
            for (x in obj.start.first until obj.start.first + obj.size.first) {
                for (y in obj.start.second downTo obj.start.second - obj.size.second + 1) {
                    usedPoints[Pair(x,y)] = usedPoints.getOrDefault(Pair(x,y), 0) + 1
                }
            }
        }
        var totalOverlappingPoints = usedPoints.filterValues { it > 1 }.count()
        println("Found $totalOverlappingPoints  overlapping area")
        return totalOverlappingPoints
    }

    private fun findOverlappingAreaPart2(input: String): Int {
        var objects = readInputToClaimsList(input)

        for (claim in objects) {
            var hasOverlap = false
            for (claim2 in objects.filter { it != claim }) {
                var check = areOverlapping(claim,claim2)
//                println("Claims ${claim.id} and ${claim2.id} overlapping? $hasOverlap")
                if (check) {
                    hasOverlap = true
                    break
                }

            }
            if (!hasOverlap) {
                return claim.id!!
            }
        }
        return 0
    }

    private fun areOverlapping(claim1: Claim, claim2: Claim): Boolean {
        var area1 = abs(claim1.bottomLeft.first - claim2.topRight.first) + abs(claim1.bottomLeft.second - claim2.topRight.second)
        var area2 = abs(claim2.bottomLeft.first - claim2.topRight.first) + abs(claim2.bottomLeft.second - claim2.bottomLeft.second)
        var x_dist = min(claim1.topRight.first, claim2.topRight.first) - max(claim1.bottomLeft.first, claim2.bottomLeft.first)
        var y_dist = min(claim1.topRight.second, claim2.topRight.second) - max(claim1.bottomLeft.second, claim2.bottomLeft.second)

        return x_dist > 0 && y_dist > 0
    }


    //   #1 @ 1,3: 4x4
    private fun readInputToClaimsList(input: String): List<Claim> {
        var claims = mutableListOf<Claim>()
        input.lines().forEach { line ->
            var id = line.substringAfter("#", "").substringBefore(" ")
            var startString = line.substringAfter("@ ", "").substringBefore(":")
            var sizeString = line.substringAfter(": ", "")
//            println("Id: $id Start: $startString Size: $sizeString")
            var startArr = startString.trim().split(",").map { it.toInt() }
            var sizeArr = sizeString.trim().split("x").map { it.toInt() }

            var claim = Claim(id.toInt(), Pair(startArr[0], startArr[1] * -1), Pair(sizeArr[0], sizeArr[1]), Pair(startArr[0], (startArr[1] * -1) - sizeArr[1]), Pair(startArr[0]+sizeArr[0], (startArr[1] * -1)))
            println("Claim: $claim")
            claims.add(claim)
        }
        return claims
    }

    // left, top (width, height)
    data class Claim(val id: Int?, val start: Pair<Int, Int>, val size: Pair<Int, Int>, val bottomLeft: Pair<Int, Int>, val topRight: Pair<Int, Int>)
    override fun partOne(input: String): Int = findOverlappingArea(input)
    override fun partTwo(input: String): Int = findOverlappingAreaPart2(input)

}