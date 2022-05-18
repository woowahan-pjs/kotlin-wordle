package wordle.domain

import java.io.FileReader
import java.time.LocalDate
import java.time.temporal.ChronoUnit

const val WORD_SIZE = 5

class Words {

    companion object {
        private val VALUE: List<String> = FileReader("src/main/resources/words.txt").readLines()
        private val BASIC_DATE = LocalDate.of(2021, 6, 19)

        fun contains(word: String): Boolean =
            VALUE.contains(word)

        fun pick(date: LocalDate): String {
            val index = ChronoUnit.DAYS.between(BASIC_DATE, date) % VALUE.size
            return VALUE[index.toInt()]
        }
    }
}

fun LocalDate.pick6() = this.toString()
