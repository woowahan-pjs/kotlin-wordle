package wordle.translation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import wordle.domain.Result
import wordle.domain.Results

class ResultsConverterTest {
    @Test
    @DisplayName("정답을 타일 형식으로로 반환합니다.")
    fun test01() {
        // arrange
        val input = Results(listOf(Result.CORRECT, Result.ABSENT, Result.PRESENT))
        val expected = "🟩⬜🟨"

        // act
        val sut: String = ResultsConverter.convert(input)

        // assert
        assertThat(sut).isEqualTo(expected)
    }
}
