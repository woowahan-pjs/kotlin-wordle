package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {

    @ParameterizedTest
    @CsvSource("10, 20, 10", "2, 5, 2", "0, 5, 0")
    fun `위치 값과 전달받은 분모값을 나눈다`(positionValue: Int, denominator: Int, expected: Int) {
        val position = Position(positionValue)

        assertThat(position.percent(denominator)).isEqualTo(expected)
    }
}
