package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordleGameLogicTest {
    @Test
    fun `이미 완전 일치 비교 한 글자는 다시 비교하지 않는다1(4번째 l)`() {
        val todayWord = Word("naval")
        val answerWord = Word("spill")

        val result = WordleGameLogic(todayWord).compare(answerWord)

        assertThat(result).isEqualTo(
            WordResult(
                mutableListOf(
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.CORRECT,
                ),
            ),
        )
    }

    @Test
    fun `이미 부분 일치 비교 한 글자는 다시 비교하지 않는다2(3번째 p`() {
        val todayWord = Word("spill")
        val answerWord = Word("paper")

        val result = WordleGameLogic(todayWord).compare(answerWord)

        assertThat(result).isEqualTo(
            WordResult(
                mutableListOf(
                    LetterMatch.PRESENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                ),
            ),
        )
    }

    @Test
    fun `1글자만 다른 단어 비교`() {
        val todayWord = Word("spill")
        val answerWord = Word("spell")

        val result = WordleGameLogic(todayWord).compare(answerWord)

        assertThat(result).isEqualTo(
            WordResult(
                mutableListOf(
                    LetterMatch.CORRECT,
                    LetterMatch.CORRECT,
                    LetterMatch.ABSENT,
                    LetterMatch.CORRECT,
                    LetterMatch.CORRECT,
                ),
            ),
        )
    }

    @Test
    fun `완전히 일치하는 단어 비교`() {
        val todayWord = Word("spill")
        val answerWord = Word("spill")

        val result = WordleGameLogic(todayWord).compare(answerWord)

        assertThat(result).isEqualTo(
            WordResult(
                mutableListOf(
                    LetterMatch.CORRECT,
                    LetterMatch.CORRECT,
                    LetterMatch.CORRECT,
                    LetterMatch.CORRECT,
                    LetterMatch.CORRECT,
                ),
            ),
        )
    }
}
