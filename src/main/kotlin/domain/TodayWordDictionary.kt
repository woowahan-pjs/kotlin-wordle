package domain

import java.io.FileReader
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class TodayWordDictionary : WordDictionary {

    private val words: List<String> = FileReader(WORD_RESOURCE).readText().split(DELIMITERS)

    override fun pickWord(): String {
        val until = ChronoUnit.DAYS.between(TARGET_DATE, LocalDate.now())

        return words[(until % words.size).toInt()]
    }

    override fun contains(target: String): Boolean {
        return words.contains(target)
    }

    companion object {
        private const val WORD_RESOURCE = "src/main/resources/words.txt"
        private const val DELIMITERS = "\n"
        private val TARGET_DATE = LocalDate.of(2021, 6, 19)
    }
}
