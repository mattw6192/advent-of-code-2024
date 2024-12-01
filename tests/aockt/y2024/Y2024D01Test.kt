package aockt.y2024


import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import io.github.jadarma.aockt.test.ExecMode

@AdventDay(2024, 1, "Total Distance")
class Y2024D01Test : AdventSpec<Y2024D01>({

    partOne {
        """
          3   4
        4   3
        2   5
        1   3
        3   9
        3   3  
        """.trimIndent() shouldOutput 11

        // not 800539 too low?
        // not 2066457 too high
    }

    partTwo {
        """
          3   4
        4   3
        2   5
        1   3
        3   9
        3   3  
        """.trimIndent() shouldOutput 31
    }



})