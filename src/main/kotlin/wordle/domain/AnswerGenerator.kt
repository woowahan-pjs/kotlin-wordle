package wordle.domain

import java.time.LocalDate

/**
 * 오늘의 단어를 추출하는 객체
 */
class AnswerGenerator(
    private val wordBook: WordBook,
) {
    /**
     * 특정 날짜에 해당하는 단어(정답)을 단어장에서 추출합니다.
     *
     * @param targetDate 특정 대상 날짜
     * @return 오늘의 단어 (정답)
     */
    fun generateAnswer(targetDate: LocalDate): Word {
        // 1. 배열의 크기를 구한다.
        val arraySize: Int = wordBook.getAllCount()

        // 2.오늘의 단어를 위한 index 추출한다.
        val answerIndex: AnswerIndex = AnswerIndex.fromDate(targetDate, arraySize)

        // 3. 오늘의 단어를 추출한다.
        return wordBook.getWord(answerIndex.value)
    }
}
