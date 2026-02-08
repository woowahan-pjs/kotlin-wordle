package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class WordTest {
    @Nested
    @DisplayName("특정 인덱스의 문자열이 같은지 판단할 때,")
    inner class test01 {
        @ParameterizedTest
        @MethodSource("wordle.domain.WordTest#expectedWords")
        @DisplayName("입력된 인덱스와 입력된 캐릭터가, Word와 동일한지 판단한다")
        fun test01(
            index: Int,
            char: Char,
            expected: Boolean,
        ) {
            // arrange
            val input: Word = Word("APPLE")

            // act
            val sut: Boolean = input.check(index, char)

            // assert
            assertThat(sut).isEqualTo(expected)
        }

        @ParameterizedTest
        @ValueSource(ints = [5, 10])
        @DisplayName("인덱스에서 벗어난 값이 들어오면 예외가 발생합니다.")
        fun test02(index: Int) {
            // arrange
            val input: Word = Word("APPLE")

            // act & assert
            assertThatThrownBy { input.check(index, 'A') }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("범위를 벗어났습니다.")
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["APPLE, 5", "CAR, 3", "A, 1"])
    @DisplayName("단어의 길이를 가져옵니다.")
    fun test02(
        input: String,
        expected: Int,
    ) {
        // arrange
        val input: Word = Word(input)

        // act
        val sut: Int = input.length()

        // assert
        assertThat(sut).isEqualTo(expected)
    }

    @Test
    @DisplayName("단어의 캐릭터 배열을 가져옵니다.")
    fun test03() {
        // arrange
        val input: Word = Word("APPLE")
        val expected: CharArray = charArrayOf('A', 'P', 'P', 'L', 'E')

        // act
        val sut: CharArray = input.toCharArray()

        // assert
        assertThat(sut).isEqualTo(expected)
    }

    @Test
    @DisplayName("단어의 문자를 기준으로 Count를 센 Map을 반환합니다.")
    fun test04() {
        // arrange
        val input: Word = Word("APPLE")

        val expected: MutableMap<Char, Int> =
            mutableMapOf(
                'A' to 1,
                'P' to 2,
                'L' to 1,
                'E' to 1,
            )

        // act
        val sut: MutableMap<Char, Int> = input.charCountMap()

        // assert
        assertThat(sut).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun expectedWords() =
            listOf(
                Arguments.of(0, 'A', true),
                Arguments.of(0, 'B', false),
            )
    }
}
