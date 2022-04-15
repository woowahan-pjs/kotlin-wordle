package edu.nextstep.wordle.application.wordle.window

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class AlphabetTest {

    @Test
    fun `알파벳은 영단어만 입력받을수 있다`() {
        val wrong = "ㄱ"

        val exception = assertThrows<IllegalArgumentException> { Alphabet(wrong) }

        assertThat(exception.message).isEqualTo("$wrong 알파벳 입력만 허용합니다.")
    }

    @Test
    fun `알파벳은 대소문자 구분을 하지 않는다`() {
        //given
        val alphabet1 = Alphabet("A")
        val alphabet2 = Alphabet("a")

        //when
        val result = alphabet1 == alphabet2

        //then
        assertThat(result).isTrue
    }

    @Test
    fun `알파벳은 서로 일치하는지 확인할 수 있다`() {
        //given
        val alphabet1 = Alphabet("a")
        val alphabet2 = Alphabet("b")

        //when
        val result = alphabet1 == alphabet2

        //then
        assertThat(result).isFalse
    }
}
