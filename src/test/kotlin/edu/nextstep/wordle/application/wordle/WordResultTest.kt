package edu.nextstep.wordle.application.wordle

import edu.nextstep.wordle.application.wordle.window.Match
import edu.nextstep.wordle.application.wordle.window.WindowResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WordResultTest {
    @Test
    fun `전체가 PERFECT 라면 true`() {
        //given
        val result = WordResult(0,
            listOf(
                WindowResult(0, Match.PERFECT),
                WindowResult(1, Match.PERFECT),
            )
        )

        //when
        val success = result.isSuccess()

        //then
        assertThat(success).isTrue
    }

    @Test
    fun `하나라도 PERFECT 가 아니라면 false`() {
        //given
        val result = WordResult(0,
            listOf(
                WindowResult(0, Match.PERFECT),
                WindowResult(1, Match.WRONG),
            )
        )

        //when
        val success = result.isSuccess()

        //then
        assertThat(success).isFalse
    }
}
