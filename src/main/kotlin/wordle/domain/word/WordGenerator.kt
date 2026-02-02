package wordle.domain.word

import java.time.LocalDate

/**
 *  - 오늘의 단어는, `((현재 날짜 - 2021년 6월 19일) % 배열의 크기)` 번째의 단어이다.
 *      - WordIndex 객체
 *          - 생성할 때 날짜, 배열의 크기
 *          - index 값을 가지고 있는
 *  - 오늘의 단어는 `words.txt`에 존재하는 단어여야한다.
 */
class WordGenerator(
    private val wordExtractor: WordExtractor,
) {
    fun generateAnswer(today: LocalDate): Word {
        // 1. 배열의 크기를 구한다.
        val arraySize: Int = wordExtractor.getSize()

        // 2.오늘의 단어를 위한 index 추출한다.
        val wordIndex: WordIndex = WordIndex.create(today, arraySize)

        // 3. 오늘의 단어를 반환한다.
        return wordExtractor.get(wordIndex.value)
    }
}
