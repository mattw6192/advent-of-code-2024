package aockt.y2018


import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import io.github.jadarma.aockt.test.ExecMode

@AdventDay(2018, 1, "Frequency")
class Y2018D01Test : AdventSpec<Y2018D01>({

    partOne {
        "+1, -2, +3, +1" shouldOutput 3
        """
          +1
          -2
          +3
          +1  
        """.trimIndent() shouldOutput 3
        "+1, +1, +1" shouldOutput 3
        "+1, +1, -2" shouldOutput 0
        "-1, -2, -3" shouldOutput -6
    }

    partTwo {
        "+1, -2, +3, +1" shouldOutput 2
        "+1, -1" shouldOutput 0
        "+3, +3, +4, -2, -4" shouldOutput 10
        "-6, +3, +8, +5, -6" shouldOutput 5
        "+7, +7, -2, -7, -4" shouldOutput 14
    }



})