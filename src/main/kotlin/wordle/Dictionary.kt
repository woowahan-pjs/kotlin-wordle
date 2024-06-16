package wordle

import wordle.domain.Word
import java.io.File
import java.time.LocalDate

object Dictionary {
    private const val PATH = "./src/main/resources"
    private const val FILE_NAME = "words.txt"
    private val words: List<String> = File(PATH, FILE_NAME).readLines()
    private val BASE_DATE = LocalDate.of(2021, 6, 19)

    fun hasWord(word: Word): Boolean {
        return words.contains(word.value)
    }

    fun findTodayWord(nowDate: LocalDate): String {
        val index = nowDate.toEpochDay().minus(BASE_DATE.toEpochDay()).toInt() % words.size
        return words[index]
    }
}