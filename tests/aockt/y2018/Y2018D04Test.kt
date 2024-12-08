package aockt.y2018


import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import io.github.jadarma.aockt.test.ExecMode

@AdventDay(2018, 4, "Repose Record")
class Y2018D04Test : AdventSpec<Y2018D04>({

    partOne {
        """
        [1518-11-01 00:00] Guard #10 begins shift
        [1518-11-01 00:05] falls asleep
        [1518-11-01 00:25] wakes up
        [1518-11-01 00:30] falls asleep
        [1518-11-01 00:55] wakes up
        [1518-11-01 23:58] Guard #99 begins shift
        [1518-11-02 00:40] falls asleep
        [1518-11-02 00:50] wakes up
        [1518-11-03 00:05] Guard #10 begins shift
        [1518-11-03 00:24] falls asleep
        [1518-11-03 00:29] wakes up
        [1518-11-04 00:02] Guard #99 begins shift
        [1518-11-04 00:36] falls asleep
        [1518-11-04 00:46] wakes up
        [1518-11-05 00:03] Guard #99 begins shift
        [1518-11-05 00:45] falls asleep
        [1518-11-05 00:55] wakes up 
        """.trimIndent() shouldOutput 240
    }


    partTwo {
        """
        [1518-11-01 00:00] Guard #10 begins shift
        [1518-11-01 00:05] falls asleep
        [1518-11-01 00:25] wakes up
        [1518-11-01 00:30] falls asleep
        [1518-11-01 00:55] wakes up
        [1518-11-01 23:58] Guard #99 begins shift
        [1518-11-02 00:40] falls asleep
        [1518-11-02 00:50] wakes up
        [1518-11-03 00:05] Guard #10 begins shift
        [1518-11-03 00:24] falls asleep
        [1518-11-03 00:29] wakes up
        [1518-11-04 00:02] Guard #99 begins shift
        [1518-11-04 00:36] falls asleep
        [1518-11-04 00:46] wakes up
        [1518-11-05 00:03] Guard #99 begins shift
        [1518-11-05 00:45] falls asleep
        [1518-11-05 00:55] wakes up 
        """.trimIndent() shouldOutput 4455
    }

    // 159987 too high
    // 62982 too high



})