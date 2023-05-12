package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import wordle.domain.Tile.*
import java.util.stream.Stream
// ktlint-disable no-wildcard-imports

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnswerTest {

    @Test
    fun 정답은_5글자여야_한다() = assertDoesNotThrow { Answer("우크라이나") }

    @ValueSource(strings = ["멕시코", "대한민국", "남아프리카공화국"])
    @ParameterizedTest
    fun 정답이_5글자가_아니면_예외가_발생한다(value: String) {
        assertThrows<IllegalArgumentException>("정답은 5글자여야 합니다.") { Answer(value) }
    }

    @Test
    fun 정답에_공백이_들어가면_예외가_발생한다() {
        assertThrows<IllegalArgumentException>("정답에 공백이 들어갈 수 없습니다.") { Answer("sds d") }
    }

    @ParameterizedTest
    @MethodSource("getAnswerJudgeTestCase")
    fun 정답과_입력값을_비교한다(answer: String, input: String, expected: List<Tile>) {
        // given
        val realAnswer = Answer(answer)

        // when
        val judgeResult = realAnswer.judge(input)

        // then
        assertThat(judgeResult).isEqualTo(expected)
    }

    private fun getAnswerJudgeTestCase(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of("auple", "poppy", listOf(GREY, GREY, GREEN, GREY, GREY)),
            Arguments.of("apple", "poppy", listOf(YELLOW, GREY, GREEN, GREY, GREY)),
            Arguments.of("hello", "label", listOf(YELLOW, GREY, GREY, YELLOW, YELLOW)),
            Arguments.of("aalll", "llbaa", listOf(YELLOW, YELLOW, GREY, YELLOW, YELLOW)),
            Arguments.of("apple", "pbbpp", listOf(YELLOW, GREY, GREY, YELLOW, GREY)),
            Arguments.of("aaall", "bbaaa", listOf(GREY, GREY, GREEN, YELLOW, YELLOW)),
            Arguments.of("aaall", "lllaa", listOf(YELLOW, YELLOW, GREY, YELLOW, YELLOW)),
        )
    }
}
