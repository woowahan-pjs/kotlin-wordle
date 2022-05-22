package wordle.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import wordle.domain.Mark.*

internal class AnswerTest {

    @Test
    fun `글자길이가 5가 아닌 경우 예외발생`() {
        assertThrows<IllegalArgumentException> { Answer("abcdef") }
            .shouldHaveMessage("[ERROR] 부적절한 글자 길이입니다.")
    }

    @Test
    fun `주어진 단어목록에 존재하지 않는 경우 예외발생`() {
        assertThrows<IllegalArgumentException> { Answer("abcde") }
            .shouldHaveMessage("[ERROR] 목록에 존재하지 않는 단어입니다.")
    }

    @Test
    fun `답안과 정답을 비교-중복되는 문자 중 하나만 일치할 때`() {
        val answer = Answer("groom")

        assertThat(answer.compareToWord("goose"))
            .isEqualTo(listOf(EXACT, NONE, EXACT, EXIST, NONE))
    }

    @Test
    fun `답안과 정답을 비교-중복되는 문자가 존재하지만 정답의 개수가 더 많을 때`() {
        val answer = Answer("eerie")

        assertThat(answer.compareToWord("sheen"))
            .isEqualTo(listOf(EXIST, EXIST, NONE, NONE, NONE))
    }
}
