package aockt.y2018


import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import io.github.jadarma.aockt.test.ExecMode

@AdventDay(2018, 2, "Checksum")
class Y2018D02Test : AdventSpec<Y2018D02>({

    partOne {
        """
          abcdef
        bababc
        abbcde
        abcccd
        aabcdd
        abcdee
        ababab  
        """.trimIndent() shouldOutput 12
    }

    partTwo {
        """
        abcde
        fghij
        klmno
        pqrst
        fguij
        axcye
        wvxyz
        """.trimIndent() shouldOutput "fgij"
    }



})