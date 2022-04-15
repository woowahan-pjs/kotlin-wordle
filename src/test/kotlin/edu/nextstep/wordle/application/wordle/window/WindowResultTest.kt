package edu.nextstep.wordle.application.wordle.window

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WindowResultTest {
    @Test
    fun `우선순위가 높은 일치결과로 교체한다`() {
        //given
        val result = WindowResult(position = 0, match = Match.MISS)

        //when
        val update = result.update(Match.PERFECT)

        //then
        assertThat(update).isEqualTo(WindowResult(position = 0, match = Match.PERFECT))
    }
}
