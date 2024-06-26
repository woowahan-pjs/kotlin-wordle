package wordle.domain

import wordle.infra.dictionaryWord
import java.time.LocalDate
import java.time.temporal.ChronoUnit

typealias TodayWord = Word

private val CRITERION_DATE: LocalDate = LocalDate.of(2021, 6, 19)

fun TodayWord(today: LocalDate): TodayWord {
    return Word(extractDictionaryWord(today))
}

private fun extractDictionaryWord(date: LocalDate): String {
    val calculatedIndex = ChronoUnit.DAYS.between(CRITERION_DATE, date).toInt()

    return dictionaryWord(calculatedIndex)
}
