package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `(성공) 단어 결과 목록을 추가할 때마다 게임을 계속 진행할 수 있는 상태인지 반환한다`(attemptCount: Int) {
        val wordResults = WordResults()

        repeat(attemptCount) {
            wordResults.addResults(WordResult())
        }

        assertThat(wordResults.isContinuousGame()).isTrue()
    }

    @Test
    fun `(성공) 단어 결과 목록이 최대로 추가되었을 때 게임을 계속할 수 없는 상태가 된다`() {
        val wordResults = WordResults()

        repeat(MAX_TRY_COUNT) {
            wordResults.addResults(WordResult())
        }

        assertThat(wordResults.isContinuousGame()).isFalse()
    }

    @Test
    fun `(예외) 단어 결과 목록을 더이상 추가할 수 없으면 예외가 발생한다`() {
        val wordResults = WordResults()

        assertThatThrownBy {
            repeat(MAX_TRY_COUNT + 1) {
                wordResults.addResults(WordResult())
            }
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("시행 횟수는 0보다 작을 수 없습니다.")
    }

    @Test
    fun `(성공) 단어 결과 목록 마지막에 모두 '완전 일치 상태'가 있어야 단어 맞추기 게임에 성공한다`() {
        val wordResults = WordResults()
        wordResults.addResults(WordResult())
        wordResults.addResults(oneAbsentWordResult)
        wordResults.addResults(onePresentWordResult)
        wordResults.addResults(MutableList(WORD_LENGTH) { LetterMatch.CORRECT }.toWordResult())

        assertThat(wordResults.isSuccessfulGame()).isTrue()
    }

    @Test
    fun `(성공) 단어 결과가 모두 '완전 일치 상태'가 아니면 단어 맞추기 게임에 성공여부가 False이다`() {
        val wordResults = WordResults()
        wordResults.addResults(oneAbsentWordResult)
        wordResults.addResults(oneAbsentWordResult)
        wordResults.addResults(oneAbsentWordResult)
        wordResults.addResults(onePresentWordResult)

        assertThat(wordResults.isSuccessfulGame()).isFalse()
    }

    private val oneAbsentWordResult =
        mutableListOf(
            LetterMatch.ABSENT,
            LetterMatch.CORRECT,
            LetterMatch.CORRECT,
            LetterMatch.CORRECT,
            LetterMatch.CORRECT,
        ).toWordResult()

    private val onePresentWordResult =
        mutableListOf(
            LetterMatch.PRESENT,
            LetterMatch.CORRECT,
            LetterMatch.CORRECT,
            LetterMatch.CORRECT,
            LetterMatch.CORRECT,
        ).toWordResult()
}
