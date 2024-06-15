package edu.nextstep.wordle.application.wordle.window

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WindowTest {
    @Test
    fun `알파벳과 위치가 일치하면 PERFECT`() {
        //given
        val window1 = Window(Alphabet("a"), 1)
        val window2 = Window(Alphabet("a"), 1)

        //when
        val match = window1.match(window2)

        //then
        assertThat(match).isEqualTo(Match.PERFECT)
    }

    @Test
    fun `알바펫만 일치하고 위치가 다르면 WRONG`() {
        //given
        val window1 = Window(Alphabet("a"), 1)
        val window2 = Window(Alphabet("a"), 2)

        //when
        val match = window1.match(window2)

        //then
        assertThat(match).isEqualTo(Match.WRONG)
    }

    @Test
    fun `알파벳 자체가 일치하지 않으면 MISS`() {
        //given
        val window1 = Window(Alphabet("b"), 1)
        val window2 = Window(Alphabet("a"), 1)

        //when
        val match = window1.match(window2)

        //then
        assertThat(match).isEqualTo(Match.MISS)
    }
}
