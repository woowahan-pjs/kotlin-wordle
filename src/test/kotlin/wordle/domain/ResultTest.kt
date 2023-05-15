package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import wordle.domain.MatchResult.GRAY
import wordle.domain.MatchResult.GREEN
import wordle.domain.MatchResult.YELLOW

class ResultTest {
    @Test
    fun `결과값은 성공여부를 알려준다`() {
        // given
        val dummy: List<MatchResult> = List(5) { GREEN }

        // when
        val result = Result(dummy)

        // then
        assertThat(result.isRight()).isTrue
    }

    @Test
    fun `결과값은 살패여부를 알려준다`() {
        // given
        val dummy: List<MatchResult> = listOf(GREEN, GRAY, GREEN, GRAY, YELLOW)

        // when
        val result = Result(dummy)

        // then
        assertThat(result.isRight()).isFalse
    }
}
