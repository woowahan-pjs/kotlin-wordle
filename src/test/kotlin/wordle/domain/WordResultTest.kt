package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordResultTest {
    @Test
    fun `답안 단어의 결과가 모두 '완전 일치'하면 게임 성공이다`() {
        val wordResult = allCorrectWordResult()

        assertThat(wordResult.isSuccessGame()).isTrue()
    }

    @Test
    fun `답안 단어의 결과가 하나라도 '완전 일치'가 아니면 게임 성공이 아니다`() {
        val wordResult = notAllCorrectWordResult()

        assertThat(wordResult.isSuccessGame()).isFalse()
    }

    @Test
    fun `답안 단어의 일치 여부를 변경한다`() {
        val wordResult = WordResult()

        wordResult.changeMatchType(0, LetterMatch.CORRECT)

        assertThat(wordResult).isEqualTo(
            WordResult(
                mutableListOf(
                    LetterMatch.CORRECT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                ),
            ),
        )
    }

    private fun allCorrectWordResult() =
        WordResult(
            mutableListOf(
                LetterMatch.CORRECT,
                LetterMatch.CORRECT,
                LetterMatch.CORRECT,
                LetterMatch.CORRECT,
                LetterMatch.CORRECT,
            ),
        )

    private fun notAllCorrectWordResult() =
        WordResult(
            mutableListOf(
                LetterMatch.ABSENT,
                LetterMatch.CORRECT,
                LetterMatch.PRESENT,
                LetterMatch.CORRECT,
                LetterMatch.CORRECT,
            ),
        )
}
