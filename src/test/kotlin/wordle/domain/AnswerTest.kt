package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import wordle.domain.Mark.*

internal class AnswerTest {

    @Test
    fun `글자길이가 5가 아닌 경우 예외발생`() {
        shouldThrow<IllegalArgumentException> { Answer("abcdef") }
            .shouldHaveMessage("[ERROR] 부적절한 글자 길이입니다.")
    }

    @Test
    fun `주어진 단어목록에 존재하지 않는 경우 예외발생`() {
        shouldThrow<IllegalArgumentException> { Answer("abcde") }
            .shouldHaveMessage("[ERROR] 목록에 존재하지 않는 단어입니다.")
    }

    @Test
    fun `답안과 정답을 비교-중복되는 입력 문자 중 하나만 정답과 일치`() {
        val answer = Answer("groom")

        answer.compareToWord("goose") shouldContainExactly listOf(EXACT, NONE, EXACT, EXIST, NONE)
    }

    @Test
    fun `답안과 정답을 비교-입력의 중복되는 문자가 정답에 포함된 개수보다 많음`() {
        val answer = Answer("eerie")

        answer.compareToWord("sheen") shouldContainExactly listOf(EXIST, EXIST, NONE, NONE, NONE)
    }
}
