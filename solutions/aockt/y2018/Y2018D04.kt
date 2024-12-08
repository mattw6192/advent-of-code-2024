package aockt.y2018

import io.github.jadarma.aockt.core.Solution
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object Y2018D04 : Solution {

    private fun solve(input: String): String {
        var guardId = 0
        var startTime = LocalDateTime.now()
        var endTime = LocalDateTime.now()
        var guards = mutableMapOf<Int, Guard>()

        input.lines().sorted().forEach { line ->
            if (line.contains("Guard")) {

                guardId = line.substringAfter("#").substringBefore(" ").toInt()

            } else if (line.contains("falls asleep")) {
                startTime = LocalDateTime.parse(line.substringAfter("[").substringBefore("]").replace(" ", "T") + ":00")
            } else {
                endTime = LocalDateTime.parse(line.substringAfter("[").substringBefore("]").replace(" ", "T") + ":00")
//                println("----- End Time $line for $guardId")
                val newSleep = Sleep(ChronoUnit.MINUTES.between(startTime, endTime), startTime, endTime)
                if (guards.containsKey(guardId)) {
                    val currentGuard = guards[guardId]
                    val currentSleeps = currentGuard?.sleeps?.toMutableList()
                    currentSleeps?.add(newSleep)
                    if (currentSleeps != null) {
                        guards[guardId]?.sleeps = currentSleeps
                    }

                } else {
                    val newGuard = Guard(guardId, mutableListOf(newSleep))
                    guards[guardId] = newGuard
                }
            }
        }

        // catch last item
        val newSleep = Sleep(ChronoUnit.MINUTES.between(startTime, endTime), startTime, endTime)
        val currentGuard = guards[guardId]
        val currentSleeps = currentGuard?.sleeps?.toMutableList()
        currentSleeps?.add(newSleep)
        if (currentSleeps != null) {
            guards[guardId]?.sleeps = currentSleeps
        }

        println("Guards: $guards")

        val sleepyGuard = guards.toList().maxByOrNull { (_, value) -> value.calculateTotalSleep() }!!.second
        val sleepyGuardMins = sleepyGuard.calculateTotalSleep()
        println("Sleepy guard: $sleepyGuard : Duration: $sleepyGuardMins")


        val minMap = mutableMapOf<Int, Int>()
        for (sleep in sleepyGuard.sleeps) {
            var currentTime = sleep.startTime
            while (currentTime != sleep.endTime) {
                val min = currentTime.minute
                minMap[min] = minMap.getOrDefault(min, 0) + 1
                currentTime = currentTime.plusMinutes(1)
            }

        }
        println("Minutes map: $minMap")

        val busyMin = minMap.maxByOrNull { it.value }?.key?.toLong()
        println("BusyMin: $busyMin")
        val answer = sleepyGuard.id.toLong() * busyMin!!

        return answer.toString()
    }

    private fun solvePart2(input: String): Int {
        val sleepMinutesPerGuard: Map<Int, List<Int>> = parseInput(input)
        return sleepMinutesPerGuard.flatMap { entry ->
            entry.value.map { minute ->
                entry.key to minute
            }
        }.mostFrequent()!!.run { first * second}
    }

    private fun parseInput(input: String): Map<Int, List<Int>> {
        val sleeps = mutableMapOf<Int, List<Int>>()
        var guard = 0
        var sleepStart = 0

        val guardPattern = """^.+ #(\d+) .+$""".toRegex()
        val timePattern = """^\[.+:(\d\d)] .+$""".toRegex()

        input.lines().sorted().forEach { line ->
            when {
                line.contains("Guard") -> guard = guardPattern.single(line).toInt()
                line.contains("asleep") -> sleepStart = timePattern.single(line).toInt()
                else -> {
                    val sleepMins = (sleepStart until timePattern.single(line).toInt()).toList()
                    sleeps.merge(guard, sleepMins) { a,b -> a + b }
                }
            }
        }
        return sleeps
    }

    override fun partOne(input: String): String = solve(input)
    override fun partTwo(input: String): Int = solvePart2(input)

}

data class Guard(val id: Int, var sleeps: MutableList<Sleep> = mutableListOf())
data class Sleep(val duration: Long, val startTime: LocalDateTime, val endTime: LocalDateTime)

private fun Guard.calculateTotalSleep(): Long {
    return this.sleeps.sumOf { it.duration }
}

private fun Regex.single(from: String): String =
    this.find(from)!!.destructured.component1()

fun <T> Iterable<T>.mostFrequent(): T? =
    this.groupBy { it }.maxBy { it.value.size }.key
