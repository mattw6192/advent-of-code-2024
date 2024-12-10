package aockt.y2024


import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import io.github.jadarma.aockt.test.ExecMode

@AdventDay(2024, 9, "Disk Fragmenter")
class Y2024D09Test : AdventSpec<Y2024D09>({

    partOne {
        "2333133121414131402".trimIndent() shouldOutput 1928
    }


   partTwo  {
       "2333133121414131402".trimIndent() shouldOutput 2858
    }

    // 84572976092 too low


})