package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import wordle.domain.ResultType.GRAY
import wordle.domain.ResultType.GREEN

class WordleCalculatorTest {

    @Test
    fun `모든 문자가 일치하면 모두 초록색을 반환한다`() {
        val wordleCalculator = WordleCalculator()

        val actual = wordleCalculator.calculate("apple", "apple")

        assertThat(actual).containsExactly(
            GREEN,
            GREEN,
            GREEN,
            GREEN,
            GREEN
        )
    }

    @Test
    fun `단어가 한 글자도 일치하지 않는 경우 모두 회색을 반환한다`() {
        val wordleCalculator = WordleCalculator()

        val actual = wordleCalculator.calculate("spill", "abcde")

        assertThat(actual).containsExactly(
            GRAY,
            GRAY,
            GRAY,
            GRAY,
            GRAY
        )
    }

    @Test
    fun `문자가 맞으면은 초록색 존재하지 않으면 회색을 반환한다`() {
        val wordleCalculator = WordleCalculator()

        val actual = wordleCalculator.calculate("spill", "spaal")

        assertThat(actual).containsExactly(
            GREEN,
            GREEN,
            GRAY,
            GRAY,
            GREEN
        )
    }
}
