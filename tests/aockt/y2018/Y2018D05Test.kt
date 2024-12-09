package aockt.y2018


import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import io.github.jadarma.aockt.test.ExecMode

@AdventDay(2018, 5, "Reactions")
class Y2018D05Test : AdventSpec<Y2018D05>({

    partOne {
        "dabAcCaCBAcCcaDA".trimIndent() shouldOutput 10
    }


    partTwo {
        "dabAcCaCBAcCcaDA".trimIndent() shouldOutput 4
    }


})