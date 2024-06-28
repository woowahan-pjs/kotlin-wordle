package wordle.domain

import java.io.File
import java.time.LocalDate
import java.time.temporal.ChronoUnit

const val WORD_SIZE = 5

object Words {

    private val VALUE: List<String> = File("src/main/resources/words.txt").readLines()

    private val BASIC_DATE = LocalDate.of(2021, 6, 19)

    fun contains(word: String): Boolean {
        return VALUE.contains(word)
    }

    fun pick(date: LocalDate): String {
        val index = ChronoUnit.DAYS.between(BASIC_DATE, date) % VALUE.size
        return VALUE[index.toInt()]
    }
}
