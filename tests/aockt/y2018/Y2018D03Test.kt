package aockt.y2018


import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import io.github.jadarma.aockt.test.ExecMode

@AdventDay(2018, 3, "Overlapping Area")
class Y2018D03Test : AdventSpec<Y2018D03>({

    partOne {
        """
        #1 @ 1,3: 4x4
        #2 @ 3,1: 4x4
        #3 @ 5,5: 2x2 
        """.trimIndent() shouldOutput 4
    }

    // 97908 too low

    partTwo {
        """
        #1 @ 1,3: 4x4
        #2 @ 3,1: 4x4
        #3 @ 5,5: 2x2 
        """.trimIndent() shouldOutput 3
    }



})