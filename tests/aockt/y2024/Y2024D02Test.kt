package aockt.y2024


import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import io.github.jadarma.aockt.test.ExecMode

@AdventDay(2024, 2, "Safe Levels")
class Y2024D02Test : AdventSpec<Y2024D02>({

    partOne {
        """
         7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9 
        """.trimIndent() shouldOutput 2
    }

    partTwo {
        """
         7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
        """.trimIndent() shouldOutput 4

        // not 409 too low
        // 425 too low
        // 426 too low
    }



})