package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RoundTest {
    @Test
    @DisplayName("라운드는 0보다 작으면 예외가 발생합니다.")
    fun test01() {
        // arrange
        val input: Int = -1

        // act & assert
        assertThatThrownBy { Round(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("라운드는 양수여야 합니다.")
    }

    @Test
    @DisplayName("다음 라운드의 경우, 현재 라운드의 +1이 됩니다.")
    fun test02() {
        // arrange
        val round: Round = Round()
        val expected: Round = Round(1)

        // act
        val sut: Round = round.next()

        // assert
        assertThat(sut).isEqualTo(expected)
    }

    @Nested
    @DisplayName("현재 라운드가 입력받은 라운드보다 값이 더 큰지 판단할 때,")
    inner class test03 {
        @Test
        @DisplayName("현재 라운드 값이 크면 true를 반환합니다.")
        fun test01() {
            // arrange
            val currentRound: Round = Round(5)
            val round: Int = 3

            // act
            val sut: Boolean = currentRound.isGreaterThanRound(round)

            // assert
            assertThat(sut).isTrue
        }

        @Test
        @DisplayName("현재 라운드 값이 작으면 false를 반환합니다.")
        fun test02() {
            // arrange
            val currentRound: Round = Round(5)
            val round: Int = 6

            // act
            val sut: Boolean = currentRound.isGreaterThanRound(round)

            // assert
            assertThat(sut).isFalse
        }
    }

    @Test
    @DisplayName("라운드의 값을 성공적으로 반환 받습니다.")
    fun test04() {
        // arrange
        val input: Int = 4
        val round: Round = Round(input)

        val expected: String = input.toString()

        // act
        val sut: String = round.toString()

        // assert
        assertThat(sut).isEqualTo(expected)
    }
}
