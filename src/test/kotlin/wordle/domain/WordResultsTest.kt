package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

class WordResultsTest {
    @Test
    fun `(성공) 단어 결과 목록은 빈 단어 결과 목록과 시도한 횟수가 0이다`() {
        val actual = WordResults()

        assertAll(
            { assertThat(actual.wordResults()).hasSize(0) },
            { assertThat(actual.attemptCount).isZero() },
        )
    }

    @Test
    fun `(성공) 단어 결과 목록은 단어 결과를 추가하고, 시도횟수가 1회 증가한다`() {
        val wordResults = WordResults()

        wordResults.addResults(WordResult())

        assertAll(
            { assertThat(wordResults.wordResults()).hasSize(1) },
            { assertThat(wordResults.attemptCount).isEqualTo(1) },
        )
    }

    @Test
    fun `(성공) 단어 결과 목록 상태가 게임을 계속 진행할 수 있는 상태인지 반환한다`() {
        val wordResults = WordResults()
        wordResults.addResults(WordResult())

        assertThat(wordResults.isContinuousGame()).isTrue()
    }

    @Test
    fun `(성공) 단어 맞추기에 성공한 경우 true 를 반환한다`() {
        val wordResults = WordResults()
        wordResults.addResults(WordResult(MutableList(WORD_LENGTH) { LetterMatch.CORRECT }))

        assertThat(wordResults.isSuccessGame()).isTrue()
    }
}
