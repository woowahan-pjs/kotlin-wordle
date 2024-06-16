package dictionary

import java.io.File
import java.time.LocalDate

object Dictionary {
  private const val PATH = "./src/main/resources"
  private const val FILE_NAME = "words.txt"
  private val words: List<String> = File(PATH, FILE_NAME).readLines()
  private val BASE_DATE = LocalDate.of(2021, 6, 19)

  fun hasWord(word: String): Boolean {
    return words.contains(word)
  }

  operator fun get(index: Int): String {
    return words[index]
  }

  fun findTodayWord(nowDate: LocalDate): String {
    val calcDate = nowDate.toEpochDay().minus(BASE_DATE.toEpochDay())
    val index: Int = (calcDate % words.size).toInt()
    return words[index]
  }
}