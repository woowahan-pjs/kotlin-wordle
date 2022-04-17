package wordle.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

// 전략패턴 필요
class Words(private val words: List<Word>) {

    fun findAnswer(position: Int): Word {
        return words[(position % words.size)]
    }

    fun contains(value: String): Boolean {
        return words.contains(Word(value))
    }
}
