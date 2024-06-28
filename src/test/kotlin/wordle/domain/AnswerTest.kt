package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import wordle.domain.Mark.EXACT
import wordle.domain.Mark.EXIST
import wordle.domain.Mark.NONE

internal class AnswerTest {

    @Test
    fun `글자길이가 5가 아닌 경우 예외발생`() {
        forAll(
            table(
                headers("answer"),
                row("abcd"),
                row("abcdef")
            )
        ) {
            shouldThrow<IllegalArgumentException> { Answer(it) }
                .shouldHaveMessage("[ERROR] 부적절한 글자 길이입니다.")
        }
    }

    @Test
    fun `주어진 단어목록에 존재하지 않는 경우 예외발생`() {
        shouldThrow<IllegalArgumentException> { Answer("abcde") }
            .shouldHaveMessage("[ERROR] 목록에 존재하지 않는 단어입니다.")
    }

    @Test
    fun `답안과 정답이 일치`() {
        val answer = Answer("apple")

        answer.compareToWord("apple") shouldContainExactly listOf(EXACT, EXACT, EXACT, EXACT, EXACT)
    }

    @Test
    fun `정답과 답안의 문자가 모두 불일치`() {
        val answer = Answer("madam")

        answer.compareToWord("rerun") shouldContainExactly listOf(NONE, NONE, NONE, NONE, NONE)
    }

    @Test
    fun `정답의 문자가 답안에 모두 존재하지만 위치가 모두 불일치`() {
        val answer = Answer("rebut")

        answer.compareToWord("brute") shouldContainExactly listOf(EXIST, EXIST, EXIST, EXIST, EXIST)
    }

    @Test
    fun `정답이 rebut이고 답안이 speed일 때 첫번째 e만 EXIST, 나머지는 NONE`() {
        val answer = Answer("speed")

        answer.compareToWord("rebut") shouldContainExactly listOf(NONE, NONE, EXIST, NONE, NONE)
    }

    @Test
    fun `정답이 erase이고 답안이 speed일 때 e와 s는 모두 EXIST, 나머지는 NONE`() {
        val answer = Answer("speed")

        answer.compareToWord("erase") shouldContainExactly listOf(EXIST, NONE, EXIST, EXIST, NONE)
    }

    @Test
    fun `정답이 spill이고 답안이 label일 때 첫번째 l은 EXIST, 두번째 l은 EXACT, 나머지는 NONE`() {
        val answer = Answer("label")

        answer.compareToWord("spill") shouldContainExactly listOf(EXIST, NONE, NONE, NONE, EXACT)
    }

    @Test
    fun `정답이 spill이고 답안이 hello일 때 첫번째 l은 EXIST, 두번째 l은 EXACT, 나머지는 NONE`() {
        val answer = Answer("hello")

        answer.compareToWord("spill") shouldContainExactly listOf(NONE, NONE, EXIST, EXACT, NONE)
    }

    @Test
    fun `정답이 mourn이고 답안이 sorry일 때 o와 두번째 r은 EXACT, 나머지는 NONE`() {
        val answer = Answer("sorry")

        answer.compareToWord("mourn") shouldContainExactly listOf(NONE, EXACT, NONE, EXACT, NONE)
    }
}
