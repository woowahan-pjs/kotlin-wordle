package wordle.domain.result

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ResultsTest {
    @Test
    @DisplayName("정답을 이모지로 반환합니다.")
    fun test01() {
        // arrange
        val input = Results(listOf(Result.CORRECT, Result.ABSENT, Result.PRESENT))
        val expected = "🟩⬜🟨"

        // act
        val sut: String = input.convert()

        // assert
        assertThat(sut).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideResultAndExpected")
    @DisplayName("Result 타입에 따라 정답 여부를 반환합니다.")
    fun test02(results: List<Result>, expected: Boolean) {
        // arrange
        val input = Results(results)

        // act
        val sut: Boolean = input.isAnswer()

        // assert
        assertThat(sut).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provideResultAndExpected(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf(Result.CORRECT, Result.CORRECT, Result.CORRECT), true),
                Arguments.of(listOf(Result.ABSENT, Result.ABSENT, Result.ABSENT), false),
                Arguments.of(listOf(Result.PRESENT, Result.PRESENT, Result.PRESENT), false)
            )
        }
    }
}
