package wordle.domain

import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import java.time.temporal.ChronoUnit

const val WORD_SIZE = 5

class Words {

    companion object {
        private val VALUE: List<String> = Files.readAllLines(Paths.get("src/main/resources/words.txt"))
        private val BASIC_DATE = LocalDate.of(2021, 6, 19)

        fun contains(word: String): Boolean {
            return VALUE.contains(word)
        }

        fun pick(date: LocalDate): String {
            val index = ChronoUnit.DAYS.between(BASIC_DATE, date) % VALUE.size
            return VALUE[index.toInt()]
        }
    }
}
