package wordle.domain

import wordle.loader.dictionaryWordsSize
import wordle.loader.indexOfDictionaryWord
import java.time.LocalDate
import java.time.temporal.ChronoUnit

private val CRITERION_DATE: LocalDate = LocalDate.of(2021, 6, 19)

fun extractWordleWord(date: LocalDate): String {
    val between = ChronoUnit.DAYS.between(CRITERION_DATE, date)
    val index = (between % dictionaryWordsSize).toInt()

    return indexOfDictionaryWord(index)
}
