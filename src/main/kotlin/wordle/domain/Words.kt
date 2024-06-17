package wordle.domain

import java.io.FileReader
import java.time.LocalDate
import java.time.temporal.ChronoUnit

const val WORD_SIZE = 5

val BASIC_DATE = LocalDate.of(2021, 6, 19)!!
val WORDS = FileReader("src/main/resources/words.txt").readLines()

fun LocalDate.pickTodayWord() =
    WORDS[(ChronoUnit.DAYS.between(BASIC_DATE, this) % WORDS.size).toInt()]

fun String.isInWords() =
    WORDS.contains(this)
