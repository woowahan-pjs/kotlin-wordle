package domain

import domain.MatchResult.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class MatchResultsTest {
    @Test
    fun `5개의 결과로 이뤄진다`() {
        // given
        val matchResult = listOf(GREEN, GREEN, GREEN, GREEN, GREEN)
        val result = MatchResults(matchResult)

        // then
        assertThat(result.results).containsExactlyElementsOf(matchResult)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 6, 7, 8])
    fun `5개의 결과가 아니면 실패한다`(size: Int) {
        // given
        val matchResult = (0 until size).map { GREEN }

        // then
        assertThatIllegalArgumentException().isThrownBy {
            MatchResults(matchResult)
        }.withMessage(MatchResults.ERROR_RESULTS_SIZE_MSG)
    }

    @Test
    fun `모든 MatchResult가 성공이면 MatchResults도 성공이다`() {
        // given
        val result = MatchResults(listOf(GREEN, GREEN, GREEN, GREEN, GREEN))

        // then
        assertThat(result.isCorrect()).isTrue
    }

    @ParameterizedTest
    @EnumSource(value = MatchResult::class, names = ["YELLOW", "GRAY"])
    fun `모든 MatchResult가 성공이 아니면 MatchResults는 성공이 아니다`(result: MatchResult) {
        // given
        val result = MatchResults(listOf(GREEN, GREEN, GREEN, GREEN, result))

        // then
        assertThat(result.isCorrect()).isFalse
    }
}
