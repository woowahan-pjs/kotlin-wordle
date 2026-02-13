package edu.nextstep.wordle.application.wordle.dictionary

import edu.nextstep.wordle.application.wordle.Word
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * 정답은 매일 바뀌며 ((현재 날짜 - 2021년 6월 19일) % 배열의 크기) 번째의 단어이다.
 */
class MemoryWordProvider(val words: List<Word>) : WordProvider {
    override fun provide(date: LocalDate): Word {
        val index = (date.format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt() - 20210619) % words.size
        return words[index]
    }
}
