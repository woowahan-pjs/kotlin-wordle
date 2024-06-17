package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WordResultsTest {
    @Test
    fun `단어 결과목록이 게임 단어 길이와 일치하지 않으면 예외를 반환한다`() {
        assertThatThrownBy { WordResult(mutableListOf()) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("단어 비교 결과는 단어 길이(5)와 일치해야 합니다.")
    }

    @Test
    fun `단어 결과목록은 빈 단어 결과 목록과 시도한 횟수가 0이다`() {
        val actual = WordResults()

        assertAll(
            { assertThat(actual.wordResults()).hasSize(0) },
            { assertThat(actual.attemptCount).isZero() },
        )
    }

    @Test
    fun `단어 결과목록은 단어 결과를 추가하고, 시도횟수가 1 증가한다`() {
        val wordResults = WordResults()

        wordResults.addResults(WordResult())

        assertAll(
            { assertThat(wordResults.wordResults()).hasSize(1) },
            { assertThat(wordResults.attemptCount).isEqualTo(1) },
        )
    }

    @Test
    fun `단어 결과 목록 상태가 게임을 계속 진행할 수 있는 상태인지 반환한다`() {
        val wordResults = WordResults()
        wordResults.addResults(WordResult())

        assertThat(wordResults.isContinuousGame()).isTrue()
    }

    @Test
    fun `단어 맞추기에 성공한 경우 true 를 반환한다`() {
        val wordResults = WordResults()
        wordResults.addResults(WordResult(MutableList(WORD_LENGTH) { LetterMatch.CORRECT }))

        assertThat(wordResults.isSuccessGame()).isTrue()
    }
}
