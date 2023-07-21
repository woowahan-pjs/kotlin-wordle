package edu.nextstep.wordle.application.wordle

import edu.nextstep.wordle.application.wordle.window.Match
import edu.nextstep.wordle.application.wordle.window.WindowResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class WordTest {
    @Test
    fun `단어는 5글자만 허용한다`() {
        //given
        val input = "abcdef"

        //when
        val exception = assertThrows<IllegalArgumentException> { Word.create(input) }

        //then
        assertThat(exception.message).isEqualTo("${input.length}: 단어의 사이즈는 5여야 합니다.")
    }

    @Test
    fun `두 단어를 비교해서 입력의 결과를 반환한다`() {
        //given
        val target = Word.create("match")
        val input = Word.create("mahal")

        //when
        val result: List<WindowResult> = target.match(input)

        //then
        assertThat(result).containsExactly(
            WindowResult(0, Match.PERFECT),
            WindowResult(1, Match.PERFECT),
            WindowResult(2, Match.WRONG),
            WindowResult(3, Match.WRONG),
            WindowResult(4, Match.MISS),
        )
    }
}
