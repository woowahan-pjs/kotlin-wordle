package wordle

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import wordle.domain.Step
import wordle.domain.Word

internal class StepTest {

    @Test
    fun `완전히 동일한 단어일 경우 전부 CORRECT`() {
        val answer = "hello"
        val word = Word("hello")

        assertThat(Step(answer, word).result).isEqualTo(Array(5) { Step.Result.CORRECT }.toList())
    }

    @Test
    fun `단어는 포함되어 있고 위치가 일치하면 CORRECT`() {
        val answer = "hello"
        val word = Word("helol")

        val resultCode = Step(answer, word).result

        // then
        assertAll(
            { assertThat(resultCode[0]).isEqualTo(Step.Result.CORRECT) },
            { assertThat(resultCode[2]).isEqualTo(Step.Result.CORRECT) },
        )
    }

    @Test
    fun `단어는 포함되어 있으나 위치가 일치하지 않을경우 MISMATCH`() {
        val answer = "hello"
        val word = Word("helol")

        val resultCode = Step(answer, word).result

        // then
        assertAll(
            { assertThat(resultCode[3]).isEqualTo(Step.Result.MISMATCH) },
            { assertThat(resultCode[4]).isEqualTo(Step.Result.MISMATCH) },
        )
    }

    @Test
    fun `단어는 포함되어 있으나 위치가 일치하지 않고 정답개수의 단어보다 입력 갯수가 더 많을 경우 일치한 횟수(왼쪽에서 오른쪽으로 계산)가 적으면 MISMATCH`() {
        val answer = "hello"
        val word = Word("heool")

        val resultCode = Step(answer, word).result

        // then
        assertThat(resultCode[2]).isEqualTo(Step.Result.MISMATCH)
    }

    @Test
    fun `단어는 포함되어 있으나 위치가 일치하지 않고 정답개수의 단어보다 입력 갯수가 더 많을 경우 일치한 횟수(왼쪽에서 오른쪽으로 계산)가 많으면 WRONG`() {
        val answer = "hello"
        val word = Word("heool")

        val resultCode = Step(answer, word).result

        // then
        assertThat(resultCode[3]).isEqualTo(Step.Result.WRONG)
    }

    @Test
    fun `단어는 포함조차 안되어있으면 WRONG`() {
        val answer = "hello"
        val word = Word("helok")

        val resultCode = Step(answer, word).result

        // then
        assertThat(resultCode[4]).isEqualTo(Step.Result.WRONG)
    }
}
